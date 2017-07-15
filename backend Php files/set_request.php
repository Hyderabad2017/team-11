<?php
include_once './Connect.php';
$obj=new Connect();
$conn= $obj->connect();
$response;
$result=$conn->prepare("INSERT INTO `student`(`userid`, `exam`, `location`, `language`, `reqid`, `date`) 
VALUES (:usid, :exam, :loc, :lang, NULL,:dt)");
$result->bindparam(':usid',$_POST["userid"],PDO::PARAM_INT);
$result->bindparam(':exam',$_POST["exam"],PDO::PARAM_STR);
$result->bindparam(':loc',$_POST["location"],PDO::PARAM_STR);
$result->bindparam(':lang',$_POST["language"],PDO::PARAM_STR);
$result->bindparam(':dt',$_POST["date"],PDO::PARAM_DATE);
$result->execute();
?>