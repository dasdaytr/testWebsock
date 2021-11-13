let url_api = 'http://localhost/';
let url_controller = 'http://localhost:8080/secured';
let friends = {};
let stompClient = null;
function connectToChat(){
    console.log("connecting");
    let socket = new SockJS(url_controller);
    stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame){
        console.log(frame);
        stompClient.subscribe("/topic/friend/"+params['id'],function (data){
            console.log(JSON.parse(data.body));

            append_friend(JSON.parse(data.body));
        })
    })
}
var params = window
    .location
    .search
    .replace('?','')
    .split('&')
    .reduce(
        function(p,e){
            var a = e.split('=');
            p[ decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
            return p;
        },
        {}
    );
function get_all_friend(){

    $.post(url_api+'allfriend',JSON.stringify({my_id:params['id']}))
        .done(function (data){
            for(let user in data){
                append_friend(data[user]);
            }
        })
}

function view_all_friend(){

}

function append_friend(friend){
    $(".container .list").append('' +
        '<li class="clearfix">\n' +
        '<form action="/friend/message" method="get">' +
        '<input type="hidden" value="'+ friend.id_chat_room + '" name="id_room">' +
        '<input type="hidden" value="'+ friend.friend_first_name+' ' +friend.friend_last_name +'" name="name">' +
        '<button type="submit">'                +
        '                <img alt="avatar" height="55px"\n' +
        '                     src="https://rtfm.co.ua/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png"\n' +
        '                     width="55px"/>\n' +
        '                <div class="about">\n' +
        '                    <div class="name">'+friend.friend_first_name+' ' +friend.friend_last_name +'</div>\n' +
        '                    <div class="status">\n' +
        '                        <i class="fa fa-circle online"></i>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '</button>' +
        '</form>' +
        '</li>')
}
get_all_friend();
connectToChat();