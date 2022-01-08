<?php
    class Database{
        private $host='db';
        private $db = 'pr1';
        private $username = 'daniel';
        private $password = '1234';
        public $conn;
        public function getConnection(){
            
            try{
                $dsn = "pgsql:host=$this->host;port=5432;dbname=$this->db;user=$this->username;password=$this->password";
                $this->conn = new PDO($dsn);
            }catch (PDOException $e){
                echo $e->getMessage();
            }
            return $this->conn;
       }
    }
?>