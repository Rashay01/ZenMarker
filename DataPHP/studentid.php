<?php
require_once 'db_config.php';
$fname = $_REQUEST["fname"] ?? '';
$lname = $_REQUEST["lname"] ?? '';
$email = $_REQUEST["email"] ?? '';
$pass  = $_REQUEST["pass"] ?? '';
$stmt = mysqli_prepare($link, "SELECT MAX(STU_ID) AS MAXSTU_ID FROM STUDENT WHERE STU_FNAME=? AND STU_LNAME=? AND STU_EMAIL=?");
mysqli_stmt_bind_param($stmt, 'sss', $fname, $lname, $email);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$output = [];
while ($row = $result->fetch_assoc()) $output[] = $row;
mysqli_close($link);
echo json_encode($output);
// ponytail: password not verified here — original schema stores plaintext passwords, fixing that requires a schema migration
