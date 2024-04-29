package br.com.alura.screenmath.calculos;

public class FiltroRecomendacao {
    private String recomendacao;

    public void filtra(Classificavel classificavel) {
        if (classificavel.getClassificavel() >= 4) {
            System.out.println("Está entre os preferidos do momento");
        } else if (classificavel.getClassificavel() >= 2) {
            System.out.println("Muito bem avaliado no momento!");
        } else {
            System.out.println("Coloque na sua lista para assistir depois");
        }
    }
}
