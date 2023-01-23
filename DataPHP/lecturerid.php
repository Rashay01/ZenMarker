<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$fname = $_REQUEST["fname"];
$lname = $_REQUEST["lname"];
$email = $_REQUEST["email"];
$pass = $_REQUEST["pass"];
$output=array();
/* Select queries return a resultset */
$d LECT_LNAME = '$lname' and LECT_EMAIL = '$email' and LECT_PASSWORD = '$pass'group by LECT_FNAME, LECT_LNAME, LECT_EMAIL,LECT_PASSWORD"))
{
        while ($row=$result->fetch_assoc()){
                $output[]=$row;
        }
}
mysqli_close($link);
echo json_encode($output);
?>