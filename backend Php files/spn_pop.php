<?php
include_once './Connect.php';
$obj=new Connect();
$conn= $obj->connect();
$response;
$res=$conn->query("Select examName from exam");
$response["result"]=1;
foreach ($res as $examNames)
{
	$response["exam"][] = ["name"=>$examNames['examName']];
}
echo json_encode($response);
?>