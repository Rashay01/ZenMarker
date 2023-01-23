<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$sid = $_REQUEST["sid"];
$gid = $_REQUEST["gid"];
$sql = "INSERT INTO STUGROUPS (STU_ID, GROUP_ID) values ($sid,$gid)";
/* Insert into STUGROUPS table*/
if ($r = mysqli_query($link, $sql)) {
        echo "Successfully joined";
}else {
        echo "Unable to join";
}
mysqli_close($link);
?>
