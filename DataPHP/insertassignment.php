<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$aname = $_REQUEST["aname"];
$adesc = $_REQUEST["adesc"];
$acourse = $_REQUEST["acourse"];
$lid = $_REQUEST["lid"];
$sql = "INSERT INTO ASSIGNMENT (ASS_NAME, ASS_DESCRIPTION, ASS_COURSE, LECT_ID) values('$aname', '$adesc','$acourse', $lid)";
/* Insert into ASSIGNMENT table*/
if ($r = mysqli_query($link, $sql)) {
        echo "record added";
}else {
        echo "Error: " . $sql . "<br>" . mysqli_error($link);
}
mysqli_close($link);
?>