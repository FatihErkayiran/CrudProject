
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "update"; %>


	<c:if test="${ product != null }">
	
		<h1>Update Product</h1>
		
	</c:if>
	
	<c:if test = "${ product == null }">
		
		<h1>Add New Product</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	
	
	<form action="<%= formType %>" method="get" >
	
		<c:if test="${ product != null }">
		
			<%-- hidden input, won't show up on the page, but will pass our id for the product --%>
			<input type="hidden" name="id" value="<c:out value='${ product.id }' />">
		
		</c:if>
		
	  <div class="form-group">
	    
	    <label for="item">Item Name</label>
	    <input type="text" class="form-control" id="item" name="item" 
	    	value="<c:out value='${ product.item }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="qty">Quantity</label>
	    <input type="number" class="form-control" id="qty" name="qty"
	    	value="<c:out value='${ product.qty }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="description">Description</label>
	    <input type="text" class="form-control" id="description" name="description"
	    	value="<c:out value='${ product.description }' />" required>
	    
	  </div>

	  
	  <button type="submit" class="btn btn-primary">Submit</button>
	  
	</form>

</div>


<%@ include file="footer.jsp" %>