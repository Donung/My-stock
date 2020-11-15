<?php   

	include("connect.php");
	
	$id = $_POST['id'];
	$product_id = $_POST['product_id'];
	$name = $_POST['name'];
	$detail = $_POST['detail'];
	$price = $_POST['price'];
	$image = $_POST['image']; 
	
	$sql = "UPDATE product SET id_product = '$product_id',
		name = '$name' ,
		detail = '$detail',
		price = '$price',
		img_url = '$image'
		WHERE id = '$id'";   

	$result = mysqli_query($con, $sql);	

	mysqli_close($con); 
?>  