<?php
require_once 'db_config.php';
$sid = $_REQUEST["sid"] ?? '';
$gid = $_REQUEST["gid"] ?? '';
$stmt = mysqli_prepare($link, "INSERT INTO TUTOGROUP (STU_ID, GROUP_ID) VALUES (?, ?)");
mysqli_stmt_bind_param($stmt, 'ss', $sid, $gid);
if (mysqli_stmt_execute($stmt)) echo "record added";
else echo "Error: " . mysqli_error($link);
mysqli_close($link);
