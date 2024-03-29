import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class CenaAmigos {


public static void main(String[] args) throws IOException {
	ArrayList<Receta> recetas = new ArrayList<Receta>();
	System.out.println("Hola, cúantas recetas quieres insertar?");
	Scanner scan = new Scanner(System.in);
	int numRecetas = scan.nextInt();
	ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

		for (int i = 0; i<numRecetas; i++) {
			Receta receta = new Receta();

			System.out.println("Dame el nombre de la receta");
			receta.setNombreReceta(scan.next());

			System.out.println("Dame el nº de ingredientes de la receta");
			int numIngredientes = scan.nextInt();

			for (int j= 0; j < numIngredientes; j++) {
				Ingrediente ingrediente = new Ingrediente();

				System.out.println("Dame el nombre del ingrediente" + (j+1));
				ingrediente.setNombreIngrediente(scan.next());
				char enGramos;
				do{
					System.out.println("La cantidad es en gramos?(Y/N)");
					enGramos = scan.next().charAt(0);
					switch (enGramos) {
						case 'Y':
							ingrediente.setEnGramos(true);
							System.out.println("Dame la cantidad en gramos");
							ingrediente.setCantidadGramos(scan.nextInt());
							break;
						case 'N':
							ingrediente.setEnGramos(false);
							System.out.println("Dame la cantidad en unidades");
							ingrediente.setCantidadUnidad(scan.nextInt());
							break;
						default:
							System.out.println("Lo siento, inténtalo otra vez");
							break;
					}
				} while((enGramos != 'Y') && (enGramos != 'N'));
	
				ingredientes.add(ingrediente); 
			}
			receta.setIngredientes(ingredientes);
			System.out.println("Finalmente, explícame brevemente su preparación");
			receta.setPreparacion(scan.next());
			recetas.add(receta);
		}

		File archivo = new File ("/home/zubiri/ProyectosJava/azterketa/recetas.txt");
		FileWriter fw = new FileWriter (archivo);

		for (int k=0; k<recetas.size(); k++) {

			fw.write(recetas.get(k).getNombreReceta() + ";");

			for (int l=0; l<ingredientes.size(); l++) {
				fw.write(ingredientes.get(l).getNombreIngrediente() + "*");
				fw.write(ingredientes.get(l).getCantidadUnidad() + "*");
				fw.write(ingredientes.get(l).getCantidadGramos() + "*");
	
				if (l == ingredientes.size() - 1) {
				fw.write(ingredientes.get(l).getEnGramos() + ";");
				}
				else {
				fw.write(ingredientes.get(l).getEnGramos() + "#");
				}

			}

			fw.write(recetas.get(k).getPreparacion() + "\n");
	
		}
	
		
		fw.close();

		FileReader fr = new FileReader(archivo);
		
		BufferedReader br2 = new BufferedReader(fr);
		
		String linea;
		
		while ((linea = br2.readLine()) != null) {
		
			System.out.println(linea);
		}

		fr.close();

	}
}

