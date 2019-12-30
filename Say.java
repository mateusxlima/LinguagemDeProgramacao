class Say {
    private int x = 0;
    private int index = 0;
    private int linhaLength = 0;
    private String text[];
    private String st = "";
    private String var = "";
    private int i = 0;
    private int n = 0;
    private int j = 0;
    private int z = 0;
    private int c = 0;
    private boolean haveMore = true;

    public void sayIt(String string) {
        string = string.trim();
        this.linhaLength = string.length();
        this.text = new String[linhaLength];
        if (string.charAt(string.length() - 1) == ')') {
            if (string.charAt(0) == 's') {
                if (string.charAt(1) == 'a') {
                    if (string.charAt(2) == 'y') {
                        if (string.charAt(3) == '(') {
                            this.x = 1;
                            this.index = 3;
                            trataString(string, index);
                        }
                    }
                }
            } else if (string.charAt(0) == 'l') {
                if (string.charAt(1) == 'n') {
                    if (string.charAt(2) == 's') {
                        if (string.charAt(3) == 'a') {
                            if (string.charAt(4) == 'y') {
                                if (string.charAt(5) == '(') {
                                    this.x = 2;
                                    this.index = 5;
                                    trataString(string, index);
                                }
                            }
                        }
                    }
                } else {
                    this.x = 0;
                }
            }
        } else {
            System.out.println("You may have an error in your SnowFlack Syntax");
        }

    }

    public void trataString(String string, int index) {
        if (validaAspas(string, index)) {
            for (this.i = index; this.i < string.length(); this.i++) {
                if (ultimoCaractere(string, this.i) || this.i + 1 == linhaLength) {
                    imprime(this.x);
                    return;
                } else {
                    if (!ehAspa(string, this.i)) {
                        this.j = this.i + 1;
                        while (string.charAt(this.j) != ',' && string.charAt(this.j) != ')') {
                            this.var += string.charAt(this.j);
                            this.j++;
                        }
                        this.var = this.var.trim();
                        this.var = Variavel.getVariavel(this.var);
                        guardaNoVetor(this.var);
                        this.var = "";
                        this.i = this.j - 1;
                    } else {
                        this.haveMore = true;
                        while (this.haveMore == true) {
                            if (ehAspa(string, this.i)) {
                                this.j = this.i + 2;
                                if (this.j > string.length()) {
                                    imprime(this.x);
                                    return;
                                } else {
                                    while (string.charAt(this.j) != '\'') {
                                        this.st += string.charAt(this.j);
                                        this.j++;
                                    }
                                    this.j++;
                                    guardaNoVetor(this.st);
                                    this.st = "";
                                    this.i = this.j;
                                }
                            } else {
                                this.haveMore = false;
                            }
                        }
                        this.i = this.i - 1;
                    }
                }
            }
        }
    }

    private void guardaNoVetor(String text) {
        this.text[z] = text;
        z++;
    }

    private boolean validaAspas(String string, int index) {
        for (this.i = index; this.i < string.length(); this.i++) {
            if (string.charAt(this.i) == '\'') {
                this.n++;
            }
        }
        if (this.n % 2 == 0) {
            return true;
        }
        return false;
    }

    private boolean ultimoCaractere(String string, int index) {
        if ((this.i + 1) == this.linhaLength) {
            return true;
        }
        return false;
    }

    private boolean ehAspa(String string, int index) {
        if (!ultimoCaractere(string, index)) {
            if (string.charAt(this.i + 1) != '\'') {
                return false;
            }
        }
        return true;
    }

    private void imprime(int forma) {
        if (forma == 2) {
            for (this.c = 0; this.c < text.length; c++) {
                if (text[c] == null) {

                } else {
                    System.out.print(text[c]);
                }
                System.out.println();
            }
        } else if (forma == 1) {
            for (this.c = 0; this.c < text.length; c++) {
                if (text[c] == null) {

                } else {
                    System.out.print("" + text[c]);
                }

            }
        } else {
            System.out.println("You may have an error in your SnowFlack Syntax");
        }
    }
}