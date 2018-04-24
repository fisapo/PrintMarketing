<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">Humber Print Marketing</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <!--home-->
        <li class="nav-item">
          <a class="nav-link" href="index.jsp"><i class="fa fa-fw fa-home"></i>Home</a>
        </li>
        <!--location CRUD for admin-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-map-marker"></i>Locations
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
            <a class="dropdown-item" href="creatLocation.jsp">Create Location</a>
            <a class="dropdown-item" href="readLocation.jsp">Read Location</a>
            <a class="dropdown-item" href="updateLocation.jsp">Update Location</a>
            <a class="dropdown-item" href="deleteLocation.jsp">Delete Location</a>
          </div>
        </li>
        <!--marketing agent CRUD for admin-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-id-card"></i>Marketing Agents
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
            <a class="dropdown-item" href="creatAgent.jsp">Create Agent</a>
            <a class="dropdown-item" href="readAgent.jsp">Read Agent</a>
            <a class="dropdown-item" href="updateAgent.jsp">Update Agent</a>
            <a class="dropdown-item" href="deleteAgent.jsp">Delete Agent</a>
          </div>
        </li>
        <!--record added by agents CRUD for admin-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-database"></i>Records
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
            <a class="dropdown-item" href="readRecord.jsp">Read Record</a>
            <a class="dropdown-item" href="updateRecord.jsp">Update Record</a>
            <a class="dropdown-item" href="deleteRecord.jsp">Delete Record</a>
          </div>
        </li>
        
        <!--client CRUD for marketing agent-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-address-book"></i>Client
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
            <a class="dropdown-item" href="creatClient.jsp">Create Client</a>
            <a class="dropdown-item" href="readClient.jsp">Read Client</a>
          </div>
        </li>
        <!--order CRUD for marketing agent-->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-shopping-cart"></i>Place Order
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
            <a class="dropdown-item" href="creatOrder.jsp">Create Order</a>
            <a class="dropdown-item" href="readOrder.jsp">Read Order</a>
            <a class="dropdown-item" href="updateOrder.jsp">Update Order</a>
            <a class="dropdown-item" href="deleteOrder.jsp">Delete Order</a>
          </div>
        </li>
        
        <!--logout-->
         <li class="nav-item">
          <a class="nav-link" href="#"><i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>