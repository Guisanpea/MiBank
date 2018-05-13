<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Objects"%>
<%@page import="mibank.entities.Employee"%>
<%@page import="mibank.entities.Transfer"%>
<%@page import="java.util.List"%>
<%
    List<Transfer> transferList = (List<Transfer>) request.getAttribute("transferList");
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
%>

<h2>List of movements</h2>
<table class="table table-hover">
    <thead class="thead-light">
        <tr>
            <th scope="col">Date </th>
            <th scope="col">Origin</th>
            <th scope="col">Amount</th>
            <th scope="col">Description</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (Transfer transfer : transferList) {
                Employee involved = transfer.getEmployeeInvolved();
                String tranferTypeColor = transfer.getAmount() > 0
                        ? "bg-success text-white" : "bg-danger text-white";
        %>
        <tr>
            <th scope="row"><%= df.format(transfer.getCreatedAt())%></th>
            <td> <%= transfer.getFromAccountBank().toString()
                   + transfer.getFromAccountOffice().toString()
                   + transfer.getFromAccountControl().toString()
                   + transfer.getFromAccountId()%></td>
            <td class="<%=tranferTypeColor%>" > <%= Math.abs(transfer.getAmount()) %></td>
            <td> <%= transfer.getDescription()%> </td>
        </tr>
        <% }%>
    </tbody>
</table>