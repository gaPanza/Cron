package br.com.cron.classes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.mail.MessagingException;

public class MainTest {
	public static void main(String[] args) throws MessagingException, URISyntaxException{
		String url = "https://ipnet.plune.com.br/JSON/Venda.PedidoItem/Browse?&_AuthToken=Ultra.Users:100-29-3822329&_Venda.PedidoItem.BrowseSequence=Id,29d7b0266a52eedb3fbd8af632fc7c16%23StatusPedido,29d7b0266a52eedb3fbd8af632fc7c16%23RepresentanteId,29d7b0266a52eedb3fbd8af632fc7c16%23Id,579e2d75a12f6766438b7350f27500ee%23NomRazaoSocial,x1_Dominio,x1_ContatoTecnicoId,x1_EmailTecnico,x1_TelefoneTecnico,x1_GerenteProjetoId,x1_GerenteProjetoEmail,x1_GerenteProjetoTelefone,x991_Id,ProdutoId,Quantidade,29d7b0266a52eedb3fbd8af632fc7c16%23TipoContratoId,x1_NivelAcompanhamento,x1_Documento,579e2d75a12f6766438b7350f27500ee%23CEPPrincipal,579e2d75a12f6766438b7350f27500ee%23PaisPrincipalId,579e2d75a12f6766438b7350f27500ee%23UFPrincipalId,579e2d75a12f6766438b7350f27500ee%23CidadePrincipalId,579e2d75a12f6766438b7350f27500ee%23BairroPrincipal,579e2d75a12f6766438b7350f27500ee%23EnderecoPrincipal,579e2d75a12f6766438b7350f27500ee%23NumeroPrincipal,579e2d75a12f6766438b7350f27500ee%23ComplementoPrincipal&_Venda.PedidoItem.BrowseLimit=10000&_Venda.PedidoItem.Order=%22Venda%22.%22PedidoItem%22.%2229d7b0266a52eedb3fbd8af632fc7c16#Id\"&__debug__=1";
		try {
			ClientWS.sendGet(url);
			ClientWS.shotEmails();
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}
