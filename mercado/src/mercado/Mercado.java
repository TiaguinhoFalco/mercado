/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

create table mercado 
(
codigo serial not null,
nome_fantasia varchar(100) not null,
razao_social varchar(100) not null,
fundacao date not null,
nr_funcionarios int not null,
valor_bolsa numeric (15,2) not null,
constraint pk_mercado primary key (codigo)
)

 */
package mercado;

/**
 *
 * @author Administrador
 */
public class Mercado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
