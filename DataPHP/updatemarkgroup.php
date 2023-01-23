<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$mark = $_REQUEST["mark"];
$aid = $_REQUEST["aid"];
$sql = "UPDATE GROUPS SET GROUP_MARK = $mark WHERE GROUP_ID = $aid";
/* Insert into GROUPS table*/
if ($r = mysqli_query($link, $sql)) {
        echo "record updated";
}else {
        echo "Error: " . $sql . "<br>" . mysqli_error($link);
}
mysqli_close($link);
?>