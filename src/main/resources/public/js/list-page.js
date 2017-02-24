/**
 * Created by Jonsy
 */
$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});

$(function () {
  ajaxClick('lock','PUT');
  ajaxClick('trash','DELETE');
});

function ajaxClick(name,type){
    $("span[name='"+name+"']").click(function () {
        $.ajax({
            type:type,
            url:$(this).attr("data"),
            success:function (data) {
                if(data){
                    alert(data);
                }else{
                    window.location.reload();
                }
            }
        })
    })
}