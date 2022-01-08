<?php
    include_once 'connectionDb.php';
    include_once 'bibl.php';
    header ('Content-type: json/application');
    $method = $_SERVER['REQUEST_METHOD'];
    $data = file_get_contents('php://input');
    $data = json_decode($data, true);
    $params = explode('/',$_GET['q']);
    $database = new Database();
    $db = $database->getConnection();
    $mybibl = new Bibl($db);
    switch($method){
        case 'GET':
            $mybibl->getBibl($params,$data);
            break;
        case 'POST':
            $mybibl->PostAddAuthor($params,$data);
            break;
        case 'PATCH':
            $mybibl->PatchUpdateAuthor($data,$params);            
            break;
        case 'DELETE':
            $mybibl->DeleteAuthor($params);
            break;
    }
?> 