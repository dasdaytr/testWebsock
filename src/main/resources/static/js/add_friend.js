let stompClient = null;
let url_api = 'http://localhost/';
let url_controller = 'http://localhost:8080/secured';
let id  = {};
function getMyId(){
    $.get(url_controller+'/id').done(function (data){
        id = data;
        $(".form-id").append(
            '<input type="hidden" name="id" value="'+ id[0] +'">'
        )
    })
}
getMyId();
connect();
/*function connectInlistFriend(){
    console.log("start friend list...");
    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({},)
}
function subscribe(frame){
    console.log("connect to: " + frame);
    stompClient.subscribe("/topic/messages/" + userId, response)
}
function  response(data){
    data = JSON.parse(data.body);
    console.log(data);
}
function sendMessage(){
    let userName ;
}*/
$(document).ready(function (){
    $(".btn").click("click", function (){
        let str = $(".str").
        val().
        trim().
        replace(/ +(?= )/g,'').
        split(' ');
        console.log(id);
        $.post( url_api + 'user', JSON.stringify({first_name:str[0], last_name: str[1]}))
            .done(function( data ) {
                for(let i in data){
                    $(".list-friend .friend ").append(
                        '<form class="form_friend" action="">' +
                        '<input type="hidden" name = "id" value="' + data[i].id + '"/>' +
                        '<input type="hidden" name = "first_name" value="' + data[i].first_name + '"/>' +
                        '<input type="hidden" name = "last_name" value="' + data[i].last_name + '"/>' +
                        '<p>'+ data[i].first_name + ' ' + data[i].last_name +'</p>' +
                        '<button class="friend_btn" >' + 'Добавить в друзья'+'</button' +
                        '</form>'
                    )
                }
            })
            .fail(function (data){
                console.log(data);
            });

    })

    $(".friend").on("click",".form_friend",function (){
        var $inputs = $(this).find(':input');
        let values = {};
        $inputs.each(function() {
            if('' !== $(this).val()){
                values[this.name] = $(this).val();
            }
        });
        let jsonUser = JSON.stringify({
            my_id:Number(id[0]),
            my_first_name: id[1],
            my_last_name:id[2],
            friend_id:values['id'],
            friend_first_name:values['first_name'],
            friend_last_name:values['last_name']});
        $.post(url_api + 'friend',jsonUser).done(function (){
            stompClient.send("/app/friend/new/" + Number(id[0]),{},JSON.stringify({
                id:id[0],
                friendid:Number(values['id']),
                friend_first_name:values['first_name'],
                friend_last_name:values['last_name']
            }));
        });
        return false;
    });
});
function connect(){
    console.log("connect");
    let socket = new SockJS(url_controller);
    stompClient = Stomp.over(socket);
    stompClient.connect();
}
