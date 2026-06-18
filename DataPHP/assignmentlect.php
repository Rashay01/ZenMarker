<?php
require_once 'db_config.php';
$LECT_ID = $_REQUEST["LECT_ID"] ?? '';
$stmt = mysqli_prepare($link, "SELECT * FROM ASSIGNMENT WHERE LECT_ID = ?");
mysqli_stmt_bind_param($stmt, 's', $LECT_ID);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$output = [];
while ($row = $result->fetch_assoc()) $output[] = $row;
mysqli_close($link);
echo json_encode($output);
