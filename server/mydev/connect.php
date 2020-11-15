<?php

	$host="localhost";
	$username="id15404288_usertest";
	$password="|t1s+tlM]L<=uT/y";
	$database="id15404288_test";
	
	$con = mysqli_connect($host, $username, $password, $database);
	
		if (mysqli_connect_errno())
		{
			echo "Database Connect Failed : " . mysqli_connect_error();
			exit();
		}

	mysqli_query($con,"SET NAMES utf8");
?>