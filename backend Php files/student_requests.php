<?php
include_once './Connect.php';
$obj=new Connect();
$conn= $obj->connect();
$response;
$res=$conn->query("SELECT exam FROM `student` WHERE userid=11");
$response["result"]=1;
foreach ($res as $examNames)
{
	$response["exam"] = ["name"=>$examNames['exam'],
							];
}
echo json_encode($response);
?>