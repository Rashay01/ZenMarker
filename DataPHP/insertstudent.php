<?php
require_once 'db_config.php';
$fname = $_REQUEST["fname"] ?? '';
$lname = $_REQUEST["lname"] ?? '';
$email = $_REQUEST["email"] ?? '';
$pass  = password_hash($_REQUEST["pass"] ?? '', PASSWORD_DEFAULT);
$stmt = mysqli_prepare($link, "INSERT INTO STUDENT (STU_FNAME, STU_LNAME, STU_EMAIL, STU_PASSWORD) VALUES (?, ?, ?, ?)");
mysqli_stmt_bind_param($stmt, 'ssss', $fname, $lname, $email, $pass);
if (mysqli_stmt_execute($stmt)) echo "record added";
else echo "Error: " . mysqli_error($link);
mysqli_close($link);
