<%@page import="support.LocalBankAttributes"%>
<%@page import="mibank.entities.Account"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Objects"%>
<%@page import="mibank.entities.Employee"%>
<%@page import="mibank.entities.Transfer"%>
<%@page import="java.util.List"%>
<%
    List<Transfer> transferList = (List<Transfer>) request.getAttribute("transferList");
    List<Transfer> madeTransferList = (List<Transfer>) request.getAttribute("madeTransferList");
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
%>
<div class="row">
    <h2>List of received movements</h2>
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
                    String tranferTypeColor = transfer.getAmount() > 0
                            ? "bg-success text-white" : "bg-danger text-white";
                    String fromAccount = Objects.nonNull(transfer.getFromAccountId())
                            ? transfer.getFromAccountBank().toString()
                            + transfer.getFromAccountOffice().toString()
                            + transfer.getFromAccountControl().toString()
                            + transfer.getFromAccountId()
                            : "Bank Movement";
            %>
            <tr>
                <th scope="row"><%= df.format(transfer.getCreatedAt())%></th>
                <td> <%= fromAccount%></td>
                <td class="<%=tranferTypeColor%>" > <%= Math.abs(transfer.getAmount())%></td>
                <td> <%= transfer.getDescription()%> </td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>
<div class="row">
    <h2>List of made movements</h2>
    <table class="table table-hover">
        <thead class="thead-light">
            <tr>
                <th scope="col">Date </th>
                <th scope="col">Destination</th>
                <th scope="col">Amount</th>
                <th scope="col">Description</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Transfer transfer : madeTransferList) {
                    String tranferTypeColor = transfer.getAmount() > 0
                            ? "bg-danger text-white" : "bg-success text-white";
                    Account destination = transfer.getAccount();
            %>
            <tr>
                <th scope="row"><%= df.format(transfer.getCreatedAt())%></th>
                <td> <%= Integer.toString(LocalBankAttributes.BANKID)
                        + Integer.toString(LocalBankAttributes.OFFICEID)
                        + Integer.toString(LocalBankAttributes.CONTROL)
                        + destination.getId()%></td>
                <td class="<%=tranferTypeColor%>" > <%= Math.abs(transfer.getAmount())%></td>
                <td> <%= transfer.getDescription()%> </td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>