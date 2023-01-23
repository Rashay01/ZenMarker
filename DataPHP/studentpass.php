<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$ID = $_REQUEST["ID"];
$output=array();
/* Select queries return a resultset */
if ($r = mysqli_query($link, "SELECT * from STUDENT where STU_ID = '$ID'")) {
        while ($row=$r->fetch_assoc()){
                $output[]=$row;
        }
}
mysqli_close($link);
echo json_encode($output);
?>