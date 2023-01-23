
<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$gname = $_REQUEST["gname"];
$gdesc = $_REQUEST["gdesc"];
$gmark = $_REQUEST["gmark"];
$assid = $_REQUEST["assid"];
$sql = "INSERT INTO GROUPS (GROUP_NAME, GROUP_DESCRIPTION, GROUP_MARK, ASS_ID) values('$gname', '$gdesc',$gmark, $assid)";
/* Insert into GROUPS table*/
if ($r = mysqli_query($link, $sql)) {
        echo "record added";
}else {
        echo "Error: " . $sql . "<br>" . mysqli_error($link);
}
mysqli_close($link);
?>