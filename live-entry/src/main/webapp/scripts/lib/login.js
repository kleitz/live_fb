/**
 * Created by lijun on 2016/11/6.
 */
function commonMask(id){
    var bodyH = $(document).height()+60;
    var bodyW = $(document).width()+60;
    $('.mask').css({'width':bodyW,'height':bodyH,'display':'block'}).animate({'opacity':"0.7"},200);
    var top = parseInt($(window).height()/2-id.height()/2+$(window).scrollTop());
    var left = parseInt($(window).width()/2-id.width()/2+$(window).scrollLeft());
    id.css({'left':left,'top':top,'display':'block'}).animate({'opacity':"1"},200);
}
function changeMask(id){
    var top = parseInt($(window).height()/2-id.height()/2);
    var left = parseInt($(window).width()/2-id.width()/2);
    id.css({'left':left,'top':top});
    var bodyH = $(document).height()+60;
    var bodyW = $(document).width()+60;
    $('.mask').css({'width':bodyW,'height':bodyH});
}