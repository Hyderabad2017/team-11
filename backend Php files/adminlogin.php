<?php
    $con = mysqli_connect("sql12.freemysqlhosting.net", "sql12185311", "AFbx57qRz7", "sql12185311");
    $email= $_POST["email"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM admin WHERE username = ? AND password = ?;");
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = false;

    if(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
    }

    echo json_encode($response);
?>