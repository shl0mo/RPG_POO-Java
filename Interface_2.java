package interfaceex2;

class Personagem {
    private int nivel;
    private int mana;
    private int mana_maxima;
    private int mana_base0;
    private int mana_base1;
    private int mana_base2;
    private int mana_base3;
    private int habilidade_0;
    private int habilidade_1;
    private int habilidade_2;
    private int ultimate;
    private int XP;
    private int XPacumulado;
    private boolean melhorias_nivel;
    
    Personagem (int mana_maxima, int mana_base0, int mana_base1, int mana_base2, int mana_base3) {
        this.mana_maxima = mana_maxima;
        this.mana = mana_maxima;
        this.mana_base0 = mana_base0;
        this.mana_base1 = mana_base1;
        this.mana_base2 = mana_base2;
        this.mana_base3 = mana_base3;
        this.habilidade_0 = 0;
        this.habilidade_1 = 0;
        this.habilidade_2 = 0;
        this.ultimate = 0;
        this.nivel = 1;
        this.XP = 0;
        this.XPacumulado = 0;
        this.melhorias_nivel = true;
    }
    
    public void adicionarXP(int quantidadeXP) {
        this.XP+= quantidadeXP;
        this.XPacumulado+= quantidadeXP;
        if (XPacumulado % 100 == 0) {
            int quantidade_niveis = this.XPacumulado/100;
            this.nivel+= quantidade_niveis;
            if (this.nivel > 25) {
                this.nivel = 25;
            }
            XPacumulado = XPacumulado - 100*quantidade_niveis;
        }
    }
    
    public int getNivel() {
        return this.nivel;
    }
    
    public boolean melhorarHabilidade(int index) {
        if (index == 0) {
            if (this.habilidade_0 > 3) {
                return false;
            } else {
                if (this.nivel == 1 && habilidade_0 == 1) {
                    return false;
                } else {
                    this.habilidade_0++;
                    return true;
                }
            }
        } else if (index == 1) {
            if (this.habilidade_1 > 3) {
                return false;
            } else {
                if (this.nivel == 1 && habilidade_1 == 1) {
                    return false;
                } else {
                    this.habilidade_1++;
                    return true;
                }
            }
        } else if (index == 2) {
            if (this.habilidade_2 > 3) {
                return false;
            } else {
                if (this.nivel == 1 && habilidade_2 == 1) {
                    return false;
                } else {
                    this.habilidade_2++;
                    return true;
                }
            }
        } else if (index == 3) {
            if (this.ultimate >= 4 || this.nivel < 6) {
                return false;
            } else if (this.ultimate < 3 && this.nivel >= 6) {
                if (this.nivel == 1 && ultimate == 1) {
                    return false;
                } else {
                    this.ultimate++;
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public boolean usarHabilidade(int index) {
        if (index == 0) {
            if (this.habilidade_0 != 0) {
                if (this.mana >= (this.mana_base0 * this.habilidade_0)) {
                   this.mana = this.mana - this.mana_base0 * this.habilidade_0;
                   return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (index == 1) {
            if (this.habilidade_1 != 0) {
                if (this.mana >= this.mana_base1 * this.habilidade_1) {
                    this.mana = this.mana - this.mana_base1 * this.habilidade_1;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (index == 2) {
            if (this.habilidade_2 != 0) {
                if (this.mana >= (this.mana_base2 * this.habilidade_2)) {
                   this.mana = this.mana - this.mana_base2 * this.habilidade_2;
                   return true;
                }
            } else {
                return false;
            }            
        } else if (index == 3) {
            if (this.ultimate != 0) {
                if (this.mana >= this.mana_base3 * this.ultimate) {
                    this.mana = this.mana - this.mana_base3 * this.ultimate;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
    
    public void consumirPocao() {
        if (this.mana != this.mana_maxima) {
            if (this.mana < this.mana_maxima) {
                if ((this.mana_maxima - this.mana) >= 350) {
                    this.mana+= 350;
                } else {
                    this.mana = this.mana_maxima;
                }   
            }
        }
    }    
}

public class InterfaceEx2 {
    private static void testarPersonagem(){
        int totalTestes = 24;
        int testesCorretos = 0;
        System.out.println("### Personagem\n");
        Personagem p = new Personagem(500, 70, 100, 10, 200);
        testesCorretos += rodarTeste("Nivel inicia em 1",p.getNivel() == 1);
        testesCorretos += rodarTeste("Nivel 1 pode melhorar habilidade",p.melhorarHabilidade(0)); // 1 0 0 0
        testesCorretos += rodarTeste("Nivel 1 pode melhorar apenas uma habilidade",!p.melhorarHabilidade(0));
        p.adicionarXP(100); //Nivel 2
        testesCorretos += rodarTeste("Personagem pode subir de nivel",p.getNivel() == 2);
        testesCorretos += rodarTeste("Personagem pode melhorar outra habilidade",p.melhorarHabilidade(1)); // 1 1 0 0
        p.adicionarXP(50);
        p.adicionarXP(50); //Nivel 3
        testesCorretos += rodarTeste("Personagem sobe de nivel mesmo recebendo experiencia aos poucos",p.getNivel() == 3);
        testesCorretos += rodarTeste("Personagem não pode melhorar ultimate antes do nivel 6",!p.melhorarHabilidade(3)); // 1 1 0 0
        p.adicionarXP(300); //Nivel 6
        testesCorretos += rodarTeste("Personagem pode chegar no nivel 6",p.getNivel() == 6);
        testesCorretos += rodarTeste("Personagem pode melhorar ultimate no nivel 6",p.melhorarHabilidade(3)); // 1 1 0 1
        testesCorretos += rodarTeste("Personagem não pode usar habilidade com nivel de melhoria 0",!p.usarHabilidade(2));
        p.melhorarHabilidade(2); // 1 1 1 1
        testesCorretos += rodarTeste("Personagem pode usar habilidade com nivel de melhoria >0",p.usarHabilidade(2));
        // atÃ© aqui: personagem nivel 6 com habilidades nivel: 1 1 1 1; mana: 490/500
        p.usarHabilidade(3);
        p.usarHabilidade(3);
        // mana 90/500
        testesCorretos += rodarTeste("Personagem não pode usar habilidade se não tiver mana suficiente",!p.usarHabilidade(3));

        // PoÃ§Ã£o
        p.consumirPocao(); // 440/500
        testesCorretos += rodarTeste("Personagem pode recuperar mana com poção",p.usarHabilidade(3));
        p.consumirPocao(); // mana 500/500
        p.consumirPocao(); // tomar outras vezes nao deveria deixar passar dos 500
        p.consumirPocao();
        p.consumirPocao();
        // mana 500/500
        p.usarHabilidade(3);
        p.usarHabilidade(3);
        p.usarHabilidade(1);
        // mana 0/500
        testesCorretos += rodarTeste("Poção não recupera além da mana maxima",!p.usarHabilidade(2));

        // Niveis mÃ¡ximos
        p.adicionarXP(2500); //Nivel 25
        testesCorretos += rodarTeste("Não é possivel passar do nivel 25",p.getNivel() == 25);
        // habilidades ainda 1 1 1 1
        p.melhorarHabilidade(0); //2 1 1 1
        p.melhorarHabilidade(0); ////3 1 1 1
        testesCorretos += rodarTeste("Habildade 0 chega ao nivel 4", p.melhorarHabilidade(0)); ////4 1 1 1
        testesCorretos += rodarTeste("Habilidade 0 não passa do nivel 4", !p.melhorarHabilidade(0));
        p.melhorarHabilidade(1); //4 2 1 1
        p.melhorarHabilidade(1); //4 3 1 1 
        testesCorretos += rodarTeste("Habilidade 1 chega ao nivel 4", p.melhorarHabilidade(1)); // 4 4 1 1
        testesCorretos += rodarTeste("Habilidade 1 não passa do nivel 4", !p.melhorarHabilidade(1));
        p.melhorarHabilidade(2); // 4 4 2 1
        p.melhorarHabilidade(2); // 4 4 3 1
        testesCorretos += rodarTeste("Habilidade 2 chega ao nivel 4", p.melhorarHabilidade(2)); //4 4 4 1
        testesCorretos += rodarTeste("Habilidade 2 não passa do nivel 4", !p.melhorarHabilidade(2));
        p.melhorarHabilidade(3); // 4 4 4 2
        testesCorretos += rodarTeste("Habilidade 3 chega ao nivel 3", p.melhorarHabilidade(3)); // 4 4 4 2 
        testesCorretos += rodarTeste("Habilidade 3 não passa do nivel 3", !p.melhorarHabilidade(3));

        p.consumirPocao();
        p.consumirPocao();
        // mesmo os 500 de mana não são suficientes para usar a ult no nivel 3 (200*3 = 600)
        testesCorretos += rodarTeste("Consumo de mana não proporcional ao nivel da habilidade", !p.usarHabilidade(3));

        mostrarResultado(testesCorretos, totalTestes);
    }

    private static int rodarTeste(String titulo, boolean resultado){
        System.out.println("- " + (resultado ? "OK" : "X ") + "\t" + titulo);
        return resultado ? 1 : 0;
    }

    private static void mostrarResultado(int testesCorretos, int totalTestes){
        System.out.println("\n> Testes corretos: " + testesCorretos + "/" + totalTestes + " ("+(100*testesCorretos/totalTestes)+"%)");
        if(testesCorretos == totalTestes){
            System.out.println("> Tudo certo!!!");
        } else {
            System.out.println(">  Ainda falta um pouquinho, mas você consegue!");
        }
    }

    public static void main(String[] args) {
        System.out.println();
        testarPersonagem();
    }
}