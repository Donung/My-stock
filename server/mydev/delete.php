<?php   

	include("connect.php");
	
	$id = $_POST['id'];

	$sql = "DELETE FROM product WHERE id = '$id'";   
	
	$result = mysqli_query($con, $sql);	
	
	mysqli_close($con); 
?>  