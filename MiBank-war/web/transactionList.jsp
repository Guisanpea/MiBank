<%@page import="mibank.entities.Transfer"%>
<%@page import="java.util.List"%>
<%
    List<Transfer> transferList = (List<Transfer>) request.getAttribute("transferList");
    %>
<table class="table table-hover">
    <thead class="thead-light">
        <tr>
            <th scope="col">Origin</th>
            <th scope="col">Date</th>
            <th scope="col">Amount</th>
            <th scope="col">Description</th>
        </tr>
    </thead>
    <tbody>
        <%if (transferList != null) { %>

        <%
            for (Transfer transfer : transferList) {
        %>
                <tr>
                    <th scope="row"> <%= transfer.getOrigin()%></th>
                    <td> <%= transfer.getCreatedAt()%> </td>
                    <td> <%= transfer.getAmount()%></td>
                    <td> <%= transfer.getDescription()%></td>
                </tr>
        <%
            }
        %>
        <%
        }
        %>

    </tbody>
</table>
