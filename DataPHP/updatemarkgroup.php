<?php
require_once 'db_config.php';
$mark = $_REQUEST["mark"] ?? '';
$aid  = $_REQUEST["aid"] ?? '';
$stmt = mysqli_prepare($link, "UPDATE GROUPS SET GROUP_MARK = ? WHERE ASS_ID = ?");
mysqli_stmt_bind_param($stmt, 'ss', $mark, $aid);
if (mysqli_stmt_execute($stmt)) echo "record updated";
else echo "Error: " . mysqli_error($link);
mysqli_close($link);
