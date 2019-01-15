/*
 * var divStr0="<div id=\"overlay\">"
    +"<div id=\"overlay_bg\"></div>"
    +"<div class=\"loading\">"
    +"<img src="../images/touch/touch_loading.gif" alt="loading" />
      <div>
        <span>正在加载中...</span>
      </div>
    </div>
    <div class="msgbox">
      <span></span>
    </div>
    <div id="pic_preview"></div>
    <div id="doc_preview"></div>
    <div id="overlay_close"></div>
  </div>
  <div id="pic_toolbar">
    <div class="turn_right"></div>
    <div class="turn_left"></div>
    <div class="zoom_plus"></div>
    <div class="zoom_minus"></div>
    <div class="pic_close"></div>
  </div>";

document.write(divStr0);
*/
//$(document).ready(function(){
    // 图片每次放大（缩小）的百分数
    var pic_plus_percent = 0.1;
    var pic_minus_percent = 0.1;
    
/*    $('a[rel="pics"]').click(function(event){
      var url = $(this).attr('href');
      var title = $(this).attr('title');
            
      showPics(event,url,title);
    });*/
    
    /*$('.preview').click(function(event){
      var url = $(this).attr('alt2');
      var title = $(this).attr('alt');
            
      showPics(event,url,title);
    }).css("cursor","hand");*/
    
    function showPics(event,url,title){
      if(/doc|docx/.test(url)){
            // 构造WORD
            //var url = $(this).attr('href');
    	  
            // 启动遮罩层
            showLoading();
            $.ajax({
              type: 'post',
              dataType: 'json',
              url: 'TFoodDetailWordPreview.jsp',
              data: {url: url},
              error: function(){
                // 显示错误信息
                showErrorMsg('很抱歉，连接超时，请稍后再试！');
              },
              success: function(json){
                if(json.error){
                  // 返回错误信息
                  showErrorMsg(json.error);
                }else {
                  var resulturl = json.url;

                  var $doc_iframe = $('<iframe />');
                  $doc_iframe.attr('src', resulturl).attr('id', 'doc_iframe').attr('scrolling', 'no');
                  var $div = $('<div />');
                  
                  var csize = getClientSize();
                  var width = Math.floor((csize[0] * 0.8));
                  var height = csize[1];
                  var left = Math.floor((csize[0] * 0.2)/2);
                  $('#doc_preview').empty()
                           .css({'width': width+'px', 'height': height+'px', 'left': left+'px'})
                           .append($doc_iframe)
                           .append($div)
                           .show();
                  
                  // 绑定WORD拖拽事件
                  bindMoveYFollowEvent($div);
                  
                  // 关闭loading层
                  $('#overlay div.loading').hide();
                  
                  // 显示关闭按钮
                  $('#overlay_close').unbind('click').bind('click', function(){
                    $('#doc_preview').empty().hide();
                    $(this).fadeOut('fast', function(){
                      $(this).hide();
                      $('#overlay').fadeOut('fast');
                    });
                  }).show();

                }
              }
            });
          }else {
            // 构造图
            var $img = $('<img/>').attr('src', url).attr('alt', title);
            showLoading();
            
            // 显示大图
            var showPreview = function(event){
              var $obj = event.data.obj;
              $('#overlay_close').unbind('click').bind('click', function(){
                $('#pic_preview').empty().hide();
                $('#pic_toolbar').fadeOut('fast', function(){
                  $(this).hide();
                });
                $(this).fadeOut('fast', function(){
                  $(this).hide();
                });
                
                hideLoading();
              }).hide();
              
              var $picview = $('<img/>');
              $picview.attr({'src': $obj.attr('src'), 'alt': $obj.attr('alt')});
              
             // 显示大图
              showEnlargePic($picview);
              // 绑定拖拽事件
              bindMoveFollowEvent($picview);
              // 显示图片工具条
              showPicToolbar($picview);
              
              $('#pic_preview').empty().append($picview).show();
              // 关闭loading层
              $('#overlay div.loading').hide();
            };
            
            if($img[0].complete){
              var params = {data: {obj: $img}};
              showPreview(params);
            }else{
              // 显示载入中图片
              $img.bind('load', {obj: $img}, showPreview);
              $img.bind('error', function() {
            	  alert("图片加载失败！");
            	  $('#pic_preview').empty().hide();
                  $('#pic_toolbar').hide();
                  $(this).hide();

          		  $('#overlay').hide();
          		  $('#overlay div.loading').hide();
              });
            }
          }
          
          event.preventDefault();
    }
    
    // 显示大图
    function showEnlargePic($obj){
      // 图片真实大小
      var pwidth = parseFloat($obj.attr('width')==undefined?$obj.get(0).width:$obj.attr('width'));
      var pheight = parseFloat($obj.attr('height')==undefined?$obj.get(0).height:$obj.attr('height'));
      // 可视窗口大小
      var wsize = getClientSize();
      var wwidth = parseFloat(wsize[0]);
      var wheight = parseFloat(wsize[1]);
      // 限制大小
      var lwidth = wwidth*0.9;
      var lheight = wheight*0.9;
      // 显示图片大小
      var rwidth, rheight;
      
      if(pwidth <= lwidth && pheight <= lheight){
        rwidth = pwidth;
        rheight = pheight;
      }else if(pwidth > lwidth && pheight <= lheight){
        rwidth = lwidth;
        rheight = (lwidth*pheight)/pwidth;
      }else if(pwidth <= lwidth && pheight > lheight){
        rwidth = lheight*pwidth/pheight;
        rheight = lheight;      
      }else {
        var testheight = lwidth*pheight/pwidth;
        if(testheight > lheight){
          // 以高度为准
          rwidth = lheight*pwidth/pheight;
          rheight = lheight;
        }else {
          // 以宽度为准
          rwidth = lwidth;
          rheight = testheight;
        }
      }
//      alert("wwidth:"+wwidth+",wheight:"+wheight+",rwidth:"+rwidth+",rheight:"+rheight);
      var pleft = (wwidth-rwidth)/2;
      var ptop = (wheight-rheight)/2;
      
      if(document.all && !window.opera){
        $obj.attr({'width': rwidth, 'height': rheight}).css({'left': pleft+'px', 'top': ptop+'px'});
      }else {
        $obj.attr({'width': rwidth, 'height': rheight}).css({'width': rwidth+'px', 'height': rheight+'px', 'left': pleft+'px', 'top': ptop+'px'});
      }
    } 
    
    // 绑定拖拽事件
    function bindMoveFollowEvent($obj){
      var drag = false;
      var nleft, ntop, nclientX, nclientY;
      $obj.mousedown(function(e){
        var $this = $(this);
        nleft = parseFloat($this.css('left'));
        ntop = parseFloat($this.css('top'));
        var ep = getEventPosition(e);
        nclientX = ep[0];
        nclientY = ep[1];
        drag = true;
        
        e.preventDefault();
      }).mousemove(function(e){
        if(drag){
          var eclientX = 0;
          var eclientY = 0;
          var ep = getEventPosition(e);
          eclientX = ep[0];
          eclientY = ep[1];
          var eleft = nleft + (eclientX - nclientX);
          var etop = ntop + (eclientY - nclientY);
          
          $obj.css({'left': eleft+'px', 'top': etop+'px'});
          e.preventDefault();
        }
      }).mouseup(function(e){
        drag = false;
      });
    }
    
    // 绑定Y轴拖拽事件
    function bindMoveYFollowEvent($obj){
      var drag = false;
      var ntop = 0, nclientY = 0;
      $obj.mousedown(function(e){
        var ep = getEventPosition(e);
        nclientY = ep[1];
        drag = true;
        
        e.preventDefault();
      }).mousemove(function(e){
        if(drag){
          var eclientY = 0;
          var ep = getEventPosition(e);
          eclientY = ep[1];
          var etop = ntop - (eclientY - nclientY);
          
          if(etop < 0){
            etop = 0;
          }
/*          else if(etop > getObjHeight($obj)){
            etop = getObjHeight($obj);
          }*/
          ntop = etop;
          
          getIFrameWindow('doc_iframe').scrollTo(0, ntop);
          e.preventDefault();
        }
      }).mouseup(function(e){
        drag = false;
      });
    }
        
    // 显示图片工具条
    function showPicToolbar($obj){
      var $pic_toolbar = $('#pic_toolbar');
      var wwidth = getClientSize()[0];
      var twidth = getObjWidth($pic_toolbar);
      $pic_toolbar.css('left', ((wwidth-twidth)/2)+'px').fadeIn('fast');
      
      // 绑定各按钮事件
      var turn_left = $pic_toolbar.find('.turn_left').attr("title","顺时针旋转90°");
      var turn_right = $pic_toolbar.find('.turn_right').attr("title","逆时针旋转90°");
      var zoom_plus = $pic_toolbar.find('.zoom_plus').attr("title","放大");
      var zoom_minus = $pic_toolbar.find('.zoom_minus').attr("title","缩小");
      var pic_close = $pic_toolbar.find('.pic_close').attr("title","关闭");
      
      // 逆时针旋转
      turn_left.unbind('click').click(function(){
        if($('#pic_preview img').get(0)){
          rotate($('#pic_preview img'), 90);
        }else {
          rotate($('#pic_preview canvas'), 90);
        }
      });
      // 顺时针旋转
      turn_right.unbind('click').click(function(){
        if($('#pic_preview img').get(0)){
          rotate($('#pic_preview img'), -90);
        }else {
          rotate($('#pic_preview canvas'), -90);
        }
      });
      // 放大
      zoom_plus.unbind('click').click(function(){
        var $this;
        if($('#pic_preview img').get(0)){
          $this = $('#pic_preview img');
        }else {
          $this = $('#pic_preview canvas');
        }
        
        if(document.all && !window.opera){
          // ie浏览器
          var pwidth = parseFloat($this.get(0).width);
          var pheight = parseFloat($this.get(0).height);
          var pwidth_plus = (pwidth*pic_plus_percent).toFixed(2)*1;
          var pheight_plus = (pheight*pic_plus_percent).toFixed(2)*1;
          
          $this.attr({'width': pwidth+pwidth_plus, 'height': pheight+pheight_plus}).animate({left: '-='+(pwidth_plus/2), top: '-='+(pheight_plus/2)});        
        }else {
          var pwidth = parseFloat($this.css('width'));
          var pheight = parseFloat($this.css('height'));  
          var pwidth_plus = (pwidth*pic_plus_percent).toFixed(2)*1;
          var pheight_plus = (pheight*pic_plus_percent).toFixed(2)*1;
                
          $this.animate({left: '-='+(pwidth_plus/2), top: '-='+(pheight_plus/2), width: '+='+pwidth_plus, height: '+='+pheight_plus}, 'fast');
        }
      });
      // 缩小
      zoom_minus.unbind('click').click(function(){
        var $this;
        if($('#pic_preview img').get(0)){
          $this = $('#pic_preview img');
        }else {
          $this = $('#pic_preview canvas');
        }
        
        if(document.all && !window.opera){
          // ie浏览器
          var pwidth = parseFloat($this.get(0).width);
          var pheight = parseFloat($this.get(0).height);
          var pwidth_plus = (pwidth*pic_minus_percent).toFixed(2)*1;
          var pheight_plus = (pheight*pic_minus_percent).toFixed(2)*1;
          
          $this.attr({'width': pwidth-pwidth_plus, 'height': pheight-pheight_plus}).animate({left: '+='+(pwidth_plus/2), top: '+='+(pheight_plus/2)});
        }else {
          var pwidth = parseFloat($this.css('width'));
          var pheight = parseFloat($this.css('height'));  
          var pwidth_plus = (pwidth*pic_minus_percent).toFixed(2)*1;
          var pheight_plus = (pheight*pic_minus_percent).toFixed(2)*1;
                
          $this.animate({left: '+='+(pwidth_plus/2), top: '+='+(pheight_plus/2), width: '-='+pwidth_plus, height: '-='+pheight_plus}, 'fast');
        }
      });
      // 关闭
      pic_close.unbind('click').click(function(){
        $('#overlay_close').click();
      });
    }
    
    // 旋转
    var rotate = function($obj, angle, whence) {
      
      if (!whence) {
        $obj.attr('angle', (($obj.attr('angle')==undefined?0:parseFloat($obj.attr('angle'))) + angle) % 360);
      } else {
        $obj.attr('angle', angle);
      }
      
      
      var obj_angle = parseFloat($obj.attr('angle'));
      var rotation;
      if (obj_angle >= 0) {
        rotation = Math.PI * obj_angle / 180;
      } else {
        rotation = Math.PI * (360+obj_angle) / 180;
      }
      
      var costheta = Math.cos(rotation);
      var sintheta = Math.sin(rotation);
    
      var $canvas;
      if (document.all && !window.opera) {
        $canvas = $('<img />');
        var canvas = $canvas.get(0);
        
        var width = $obj.get(0).width;
        var height = $obj.get(0).height;
        var clientSize = getClientSize();
        var left = Math.floor((clientSize[0]-parseFloat(width))/2);
        var top = Math.floor((clientSize[1]-parseFloat(height))/2);
        $canvas.attr({'src': $obj.attr('src')}).css({'left': left+'px', 'top': top+'px'});
        canvas.width = width;
        canvas.height = height;
        $canvas.css('filter', "progid:DXImageTransform.Microsoft.Matrix(M11="+costheta+",M12="+(-sintheta)+",M21="+sintheta+",M22="+costheta+",SizingMethod='auto expand')");
        
        // 绑定拖拽事件
        bindMoveFollowEvent($canvas);
      } else {
        $canvas = $('<canvas />');
        var canvas = $canvas.get(0);
        var oImage;
        if(!$obj.get(0).oImage){
          oImage = new Image();
          oImage.src = $obj.attr('src');
          canvas.oImage = oImage;
        }else {
          oImage = $obj.get(0).oImage;
          canvas.oImage = oImage;
        }
        
        var cwidth = parseFloat($obj.css('width'));
        var cheight = parseFloat($obj.css('height'));     
        // 每旋转一次对调宽高
        canvas.width = cheight;
        canvas.height = cwidth;
        
        var clientSize = getClientSize();
        var left = Math.floor((clientSize[0]-canvas.width)/2);
        var top = Math.floor((clientSize[1]-canvas.height)/2);
        
        $canvas.css({'width': canvas.width+'px', 'height': canvas.height+'px', 'left': left+'px', 'top': top+'px'});
        
        var context = $canvas.get(0).getContext('2d');
        context.save();
        
        if (rotation <= Math.PI/2) {
          context.translate(sintheta*cheight,0);
        } else if (rotation <= Math.PI) {
          context.translate(canvas.width,-costheta*cwidth);
        } else if (rotation <= 1.5*Math.PI) {
          context.translate(-costheta*cwidth,canvas.height);
        } else {
          context.translate(0,-sintheta*cwidth);
        }
        
        context.rotate(rotation);
        if(rotation % Math.PI == 0){
          context.drawImage(oImage, 0, 0, cheight, cwidth);
        }else {
          context.drawImage(oImage, 0, 0, cwidth, cheight);
        }
        context.restore();    
        
        // 绑定拖拽事件
        bindMoveFollowEvent($canvas); 
      }
      
      $canvas.attr('angle', obj_angle);
      $obj.replaceWith($canvas);
    }   
       
	// 启动loading
	function showLoading(){
		$('#overlay_bg').css('opacity', '0.3');
		var $overlay = $('#overlay').fadeIn('fast');
		var $loading = $overlay.find('.loading');
		$loading.css({'left': ((getObjWidth($overlay) - getObjWidth($loading))/2)+'px', 'top': ((getObjHeight($overlay) - getObjHeight($loading))/2)+'px'}).show();
	}
	// 关闭loading
	function hideLoading(){
		$('#overlay').fadeOut('fast');
		$('#overlay div.loading').hide();
	}
    
    // 显示错误信息
    function showErrorMsg(msg){
      var $overlay = $('#overlay');
      var $msgbox = $overlay.find('div.msgbox');
      $msgbox.find('span').text(msg);
      $overlay.find('div.loading').fadeOut('fast', function(){
        $msgbox.css({'left': ((getObjWidth($overlay) - getObjWidth($msgbox))/2)+'px', 'top': ((getObjHeight($overlay) - getObjHeight($msgbox))/2)+'px'}).show();
        $(this).hide();
      });
      $overlay.bind('click', function(){
        $overlay.fadeOut('fast');
        $msgbox.fadeOut('fast', function(){
          $(this).hide();
        });
        $(this).unbind('click');
      });   
    }
  
    // 获取窗口宽高
    function getClientSize(){
      var ch = document.documentElement.clientHeight | window.innerHeight;
      var cw = document.documentElement.clientWidth | window.innerWidth;
      if(ch==null||ch==0||cw==null||cw==0){
    	  var viewportObj=getViewport();
    	  ch=viewportObj.height;
    	  cw=viewportObj.width;
      }
      return [cw, ch];
    }
    
    /**
     * @author ZhuHL
     * @since 20150825
     * BackCompat：标准兼容模式关闭。
     * CSS1Compat：标准兼容模式开启。
     * 
     * 当document.compatMode等于BackCompat时，浏览器客户区宽度是document.body.clientWidth；
     * 当document.compatMode等于CSS1Compat时，浏览器客户区宽度是document.documentElement.clientWidth。
     */
    function getViewport(){
        if (document.compatMode == "BackCompat"){
        	// 整个网页的大小
            return{
                width :document.body.clientWidth,
                height:document.body.clientHeight
            }
        }else{
        	// 一个div的大小
            return{
                width :document.documentElement.clientWidth,
                height:document.documentElement.clientHeight
            }
        }
    }
    function getViewport4Scroll(){
        if (document.compatMode == "BackCompat"){
        	// 整个网页的大小
            return{
            	scrollLeft :document.body.scrollLeft,
            	scrollTop:document.body.scrollTop
            }
        }else{
        	// 一个div的大小
            return{
            	scrollLeft :document.documentElement.scrollLeft,
            	scrollTop:document.documentElement.scrollTop
            }
        }
    }
    
    
    // 获得元素宽度
    function getObjWidth($obj){
      return parseFloat($obj.width()==0?$obj.get(0).width:$obj.width());
    }
    
    // 获得元素高度
    function getObjHeight($obj){
      return parseFloat($obj.height()==0?$obj.get(0).height:$obj.height());
    }
    
    // 获取事件当前位置
    function getEventPosition(e){
      var nclientX = 0, nclientY = 0;
      if(!document.all){
        nclientX = e.pageX;
        nclientY = e.pageY;         
      }else {
    	var _obj=getViewport4Scroll();
        nclientX = e.clientX + _obj.scrollLeft;
        nclientY = e.clientY + _obj.scrollTop;
      }
      
      return [nclientX, nclientY];    
    }
    
    // 获得iframe的window
    function getIFrameWindow(id){
      return document.frames?document.frames(id):document.getElementById(id).contentWindow;
    }
//});