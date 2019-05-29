package CamadaLogica;

//Singleton
public class Banco 
{
   private static Conexao conexao;
   private Banco(){}
   static public boolean conectar()
   {
      conexao=new Conexao("", 
                       "jdbc:postgresql://localhost/", "sgg",
                      "postgres","postgres123");
      return conexao.getEstadoConexao();
   }
   public static Conexao getCon()
   {
       return conexao;
   }    
}
