
<%@ include file="header.jsp" %>

<div class="container">
	
	<h1>Product List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>#</th>
				<th>Item</th>
				<th>Quantity</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="product" items="${ allProducts }">
				
				<tr>
					<td>
						<c:out value="${ product.id }" />
					</td>
					
					<td>
						<c:out value="${ product.item }" />
					</td>
					
					<td>
						<c:out value="${ product.qty }" />
					</td>
					
					<td>
						<c:out value="${ product.description }" />
					</td>
					
					<td>
						
						<a href="edit?id=<c:out value='${ product.id }' />">
							<button class="btn btn-primary">Edit</button>
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?id=<c:out value='${ product.id }' />">
						
							<button class="btn btn-danger">Delete</button>
							
						</a>
						
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>