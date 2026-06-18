<?php
require_once 'db_config.php';
$aname   = $_REQUEST["aname"] ?? '';
$adesc   = $_REQUEST["adesc"] ?? '';
$acourse = $_REQUEST["acourse"] ?? '';
$lid     = $_REQUEST["lid"] ?? '';
$stmt = mysqli_prepare($link, "INSERT INTO ASSIGNMENT (ASS_NAME, ASS_DESCRIPTION, ASS_COURSE, LECT_ID) VALUES (?, ?, ?, ?)");
mysqli_stmt_bind_param($stmt, 'ssss', $aname, $adesc, $acourse, $lid);
if (mysqli_stmt_execute($stmt)) echo "record added";
else echo "Error: " . mysqli_error($link);
mysqli_close($link);
