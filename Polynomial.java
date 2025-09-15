class Polynomial {
      double[] coef;

      public Polynomial(){
            coef = new double[1];
            coef[0] = 0.0;	 
      }

      public Polynomial(double[] arg){
            coef = arg;
      }

      public Polynomial add(Polynomial arg){
            double[] longer, shorter;
            if(arg.coef.length > this.coef.length){
                  longer = arg.coef;
                  shorter = this.coef;
            }
            else{
                  longer = this.coef;
                  shorter = arg.coef;
            }
            int cap = shorter.length;
            for(int i = 0; i < cap; i++){
                  longer[i] = longer[i] + shorter[i];
            }
            return new Polynomial(longer);
      }

      public double evaluate(double arg){
            int len = coef.length;
            double result = 0.0;
            for(int i=0; i<len; i++){
                  result += Math.pow(arg, i) * this.coef[i];
            }
            return result;
      }

      public boolean hasRoot(double arg){
            return evaluate(arg)==0;
      }
}