<?php
class Connect{
private $conn;
function construct(){

}

function connect(){
try{
$this->conn=new PDO('mysql:host=sql12.freemysqlhosting.net;dbname=sql12185311','sql12185311','AFbx57qRz7');
}
catch(PDOException $e){
print('Error'.$e->getmessage());
die();
}
return $this->conn;
}
}
?>