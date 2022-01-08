<?php
header("Content-Type: application/json; charset=UTF-8");
    class Bibl{
        private $conn;
        public function __construct($db){
            $this->conn = $db;
        }
        public function getBibl($url,$params){
            switch($url[0]){
                case 'friend':
                    $this->GETAllAuthor($params);
                    break;
                 case 'users':
                    $this->getAllAuthorInDataBase();
                    break;
                case 'allmessage':
                    $response = $this->conn->prepare("SELECT * FROM message WHERE id_chat_room=:id_chat_room");
                    $response->execute($params);
                    $array = $response->fetchAll(PDO::FETCH_ASSOC);
                    echo json_encode($array);
                    break;
                default:
                    http_response_code(404);
                    $res = [
                        "status" => false,
                        "message" => "there is no such page"
                    ];
                    echo json_encode($res);
            }

        }
        public function DeleteAuthor($url){
            $type = $url[0];
            $id = $url[1];
            if($type =='author'){
                if(isset($id)){
                    $sth = $this->conn->prepare("DELETE FROM author WHERE id = ?");
                    $sth->execute([$id]);
                    $res = [
                        "status" => true,
                        "message" => "delete succeful"
                    ];
                    echo json_encode($res);
                }else{
                    http_response_code(404);
                    $res = [
                    "status" => false,
                    "message" => "error"
                    ];
                    echo json_encode($res);
                }

            }else{
                http_response_code(404);
                $res = [
                    "status" => false,
                    "message" => "there is no such page"
                ];
                echo json_encode($res);
            }

        }
        public function PatchUpdateAuthor($array,$url){
            $id = $array['id'];
            $author = $array['author'];
            $type = $url[0];
            if($type =='author'){
                $sth = $this->conn->prepare("UPDATE author SET author=? WHERE id = ?");
                $sth->execute([$author,$id]);
                http_response_code(200);
                $res = [
                    "status" => true,
                    "message" => "update succeful"
                ];
                echo json_encode($res);
            }else{
                http_response_code(404);
                $res = [
                    "status" => false,
                    "message" => "there is no such page"
                ];
                echo json_encode($res);
            }
        }
        public function PostAddAuthor($url,$params){
            $id_chat_room = time();
            $type = $url[0];
            if($type =='allmessage'){
                $response = $this->conn->prepare("SELECT * FROM message WHERE id_chat_room=:id_chat_room");
                $response->execute($params);
                $array = $response->fetchAll(PDO::FETCH_ASSOC);
                echo json_encode($array);
            }
            elseif($type =='allfriend'){
                $this->GETAllAuthor($params);
                exit();
            }
            elseif ($type =='user'){
                $this->GETOneAuthor($params);
            }
            elseif($type == 'friend'){
                $sth = $this->conn->prepare("INSERT INTO friends (friend_id,id_chat_room,my_id, friend_first_name,friend_last_name) VALUES (:friend_id,:id_chat_room,:my_id,:friend_first_name,:friend_last_name)");
                $sth->execute(array('friend_id'=> $params['friend_id'],'id_chat_room'=>$id_chat_room,'my_id'=>$params['my_id'],'friend_first_name'=>$params['friend_first_name'],'friend_last_name'=>$params['friend_last_name']));
                $sth = $this->conn->prepare("INSERT INTO friends (friend_id,id_chat_room,my_id, friend_first_name,friend_last_name) VALUES (:friend_id,:id_chat_room,:my_id,:my_first_name,:my_last_name)");
                $sth->execute(array('friend_id'=> $params['my_id'],'id_chat_room'=>$id_chat_room,'my_id'=>$params['friend_id'],'my_first_name'=>$params['my_first_name'],'my_last_name'=>$params['my_last_name']));
                http_response_code(201);
                $res = [
                    "status" => true,
                    "author_id" => $this->conn->lastInsertId()
                ];
                echo json_encode($res);
            }elseif($type == 'message'){
                $sth = $this->conn->prepare("INSERT INTO message (id_chat_room,from_user,message) VALUES (:id_chat_room,:from_user,:message)");
                $sth->execute($params);
                http_response_code(201);
                $res = [
                    "status" => true,
                    "author_id" => $this->conn->lastInsertId()
                ];
                echo json_encode($res);
            }else{
                http_response_code(404);
                $res = [
                    "status" => false,
                    "message" => "there is no such page"
                ];
                echo json_encode($res);
            }
        }
        private function GETAllAuthor($params){
            $response = $this->conn->prepare("SELECT id_chat_room,my_id,friend_id, friend_first_name, friend_last_name FROM user_web_socket  RIGHT JOIN friends on user_web_socket.id=friends.my_id where user_web_socket.id= :id");
            $response->execute(array('id'=>$params['my_id']));
            $array = $response->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($array);
        
        }
        private function getAllAuthorInDataBase(){
            $response = $this->conn->prepare("SELECT id, first_name, last_name FROM user_web_socket");
            $response->execute();
            echo json_encode($response->fetchAll(PDO::FETCH_ASSOC));
        }
        private function GETOneAuthor($params){
            $first_name = $params['first_name'];
            $last_name = $params['last_name'];
            $response = $this->conn->prepare("SELECT id,first_name,last_name FROM user_web_socket WHERE first_name=:first_name and last_name=:last_name");
            $response->execute(array('first_name' =>$first_name, 'last_name' =>$last_name));
            $array = $response->fetchAll(PDO::FETCH_ASSOC);
            if(!empty($array)){
                http_response_code(200);
                echo json_encode($array);
            }else{
                http_response_code(404);
                $res = [
                    "status" => false,
                    "message" => "author not found"
                ];
                echo json_encode($res);
            }
        }
    }
?>