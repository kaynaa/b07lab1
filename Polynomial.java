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
            double[] longer, shorter, result;
            if(arg.coef.length > this.coef.length){
                  longer = arg.coef;
                  shorter = this.coef;
            }
            else{
                  longer = this.coef;
                  shorter = arg.coef;
            }
            int len = longer.length;
            int cap = shorter.length;
            result = new double[len];
            for(int i = 0; i < len; i++){
                  result[i] = longer[i];
                  if(i < cap) result[i] += shorter[i];
            }
            return new Polynomial(result);
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