<?php
require_once 'db_config.php';
$gname = $_REQUEST["gname"] ?? '';
$gdesc = $_REQUEST["gdesc"] ?? '';
$gmark = $_REQUEST["gmark"] ?? '';
$assid = $_REQUEST["assid"] ?? '';
$stmt = mysqli_prepare($link, "INSERT INTO GROUPS (GROUP_NAME, GROUP_DESCRIPTION, GROUP_MARK, ASS_ID) VALUES (?, ?, ?, ?)");
mysqli_stmt_bind_param($stmt, 'ssss', $gname, $gdesc, $gmark, $assid);
if (mysqli_stmt_execute($stmt)) echo "record added";
else echo "Error: " . mysqli_error($link);
mysqli_close($link);
