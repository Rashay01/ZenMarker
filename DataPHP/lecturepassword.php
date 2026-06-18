<?php
require_once 'db_config.php';
$ID = $_REQUEST["ID"] ?? '';
$stmt = mysqli_prepare($link, "SELECT * FROM LECTURER WHERE LECT_ID = ?");
mysqli_stmt_bind_param($stmt, 's', $ID);
mysqli_stmt_execute($stmt);
$result = mysqli_stmt_get_result($stmt);
$output = [];
while ($row = $result->fetch_assoc()) $output[] = $row;
mysqli_close($link);
echo json_encode($output);
