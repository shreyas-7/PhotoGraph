
package com.voidwalkers.photograph.MatrixFragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.voidwalkers.photograph.R ;
import org.apache.commons.math3.linear.* ;

public class Matrix {

    private int NumberofRows, NumberofCols;

    private float Elements[][]=new float[9][9];

    public  Matrix(int r,int c) {
        this.NumberofRows=r;
        this.NumberofCols=c;
    }

    public Matrix(int p) {
        NumberofRows=p;
        NumberofCols=p;
    }

    public Matrix() {
        NumberofRows=9;
        NumberofCols=9;
    }

    public int GetRow()
    {
        return NumberofRows;
    }

    public int GetCol()
    {
        return NumberofCols;
    }

    public void SetRow(int r)
    {
        NumberofRows=r;
    }

    public void SetCol(int c)
    {
        NumberofCols=c;
    }

    public boolean is_squareMatrix()
    {
        return (NumberofCols==NumberofRows);
    }

    public void MakeIdentity() {
        for (int i=0;i<this.GetRow();i++){
            for (int j=0;j<this.GetCol();j++)
            {
                if(i==j)
                    this.Elements[i][j]=1;
                else
                    this.Elements[i][j]=0;
            }
        }

    }

    public void AddtoThis(Matrix m) {
        if(isSameOrder(m)) {
            for (int i = 0; i < m.GetRow(); i++)
                for (int j = 0; j < m.GetCol(); j++)
                    this.Elements[i][j] = this.Elements[i][j] + m.Elements[i][j];
        }
    }

    public void SubtoThis(Matrix m) {
        if(isSameOrder(m)) {
            for (int i = 0; i < m.GetRow(); i++)
                for (int j = 0; j < m.GetCol(); j++)
                    this.Elements[i][j] = this.Elements[i][j] - m.Elements[i][j];
        }
    }

    private boolean isSameOrder(Matrix a) {
        return (a.GetRow()==this.GetRow()&&a.GetCol()==this.GetCol());
    }

    private void CopyThisto(Matrix p) {
        if(isSameOrder(p))
        {
            p.Elements = this.Elements.clone();
        }

    }

    private void CopyFrom(Matrix p) {
        if(isSameOrder(p))
        {

            this.Elements = p.Elements.clone();
        }
    }

    public float GetTrace() throws IllegalStateException{
        float trace=0;
        if(!this.is_squareMatrix())
            throw new IllegalStateException("Matrix must be Square");
        else {
            for(int i=0;i<this.GetRow();i++)
                        trace+=this.GetElementof(i,i);
            return trace;
        }
    }

    public Matrix Transpose() {
        Matrix p = new Matrix(this.GetCol(),this.GetRow());
        for(int i=0;i<this.GetRow();i++)
            for (int j=0;j<this.GetCol();j++)
                p.Elements[j][i]=this.Elements[i][j];
        return p;
    }

    public void SquareTranspose() {
        Matrix p = new Matrix(this.GetCol());
        for(int i=0;i<this.GetRow();i++)
            for (int j=0;j<this.GetCol();j++)
                p.Elements[j][i]=this.Elements[i][j];
        this.CopyFrom(p);
    }

    private boolean AreMultipliabe(Matrix h)
    {
        return this.GetCol()==h.GetRow();
    }

    @Nullable
    private Matrix MultipyWith(Matrix j) throws Exception{
        if(AreMultipliabe(j))
        {
            Matrix m= new Matrix(this.GetRow(),j.GetCol());
            for(int i=0;i<this.GetRow();i++)
                for(int js=0;js<m.GetCol();js++)
                {
                    m.Elements[i][js]=0;
                    for(int k=0;k<this.GetCol();k++)
                    {
                        m.Elements[i][js]+=this.Elements[i][k]*j.Elements[k][js];
                    }
                }

            return m;
        }
        else {
            throw new Exception("Matrix Could not be multiplied still called MultiplyMethod");
        }

    }

    public void MultiplytoThis(Matrix m) throws ExceptionInInitializerError{
        if (this.AreMultipliabe(m)) {
            Matrix m1 = new Matrix(this.GetRow(), m.GetCol());
            for (int i = 0; i < this.GetRow(); i++)
                for (int js = 0; js < m.GetCol(); js++) {
                    m1.Elements[i][js] = 0;
                    for (int k = 0; k < this.GetCol(); k++) {
                        m1.Elements[i][js] += this.Elements[i][k] * m.Elements[k][js];
                    }
                }
            this.CloneFrom(m1);
        }
        else{
            throw new ExceptionInInitializerError();
        }
    }

    private void PushAt(int R_index,int C_index,float Elt)
    {
        this.Elements[R_index][C_index]=Elt;
    }

//    public float GetDeterminant(ProgressDialog px) {
//        float  Result=0;
//        int total = 0;
//        int flag=0,a=0,b=0;
//        int Order =this.GetRow();
//        if(Order==1)
//        {
//            Result= this.Elements[0][0];
//            px.setProgress(100);
//            return Result;
//        }
//        if(Order==2)
//        {
//            float l=this.Elements[0][0]*this.Elements[1][1];
//            float m=this.Elements[1][0]*this.Elements[0][1];
//            Result=l-m;
//            px.setProgress(100);
//            return Result;
//
//        }
//        else
//        {
//            for(;flag<Order;flag++)
//            {
//                Matrix pointer= new Matrix(Order-1);
//                for(int i=1;i<Order;i++)
//                {
//                    px.setProgress((total*100)/(Order*Order));
//                    total++;
//                    for(int j=0;j< Order;j++)
//                    {
//                        if(flag!=j)
//                        {
//                            float pg=this.Elements[i][j];
//                            pointer.PushAt(a,b,pg);
//                            b++;
//                        }
//                    }
//                    a++;
//                    b=0;
//
//                }
//                a=0;
//                b=0;
//                float z=pointer.GetDeterminant();
//                Result +=Math.pow(-1,flag)*(this.Elements[0][flag]*z);
//            }
//        }
//        px.setProgress(100);
//        return Result;
//    }

    public float GetDeterminant() {
        float  Result=0;
        int flag=0,a=0,b=0;
        int Order=this.GetRow();
        if(Order==1)
        {
            Result= this.Elements[0][0];
            return Result;
        }
        if(Order==2)
        {
            float l=this.Elements[0][0]*this.Elements[1][1];
            float m=this.Elements[1][0]*this.Elements[0][1];
            Result=l-m;
            return Result;

        }
        else
        {
            for(;flag<Order;flag++)
            {
                Matrix pointer= new Matrix(Order-1);
                for(int i=1;i<Order;i++)
                {
                    for(int j=0;j< Order;j++)
                    {
                        if(flag!=j)
                        {
                            float pg=this.Elements[i][j];
                            pointer.PushAt(a,b,pg);
                            b++;
                        }
                    }
                    a++;
                    b=0;
                }
                a=0;
                b=0;
                float z=pointer.GetDeterminant();
                Result +=Math.pow(-1,flag)*(this.Elements[0][flag]*z);
            }
        }

        return Result;
    }

    private void MakeAdjoint(ProgressDialog Progress) {
        int Order=this.GetCol();
        Matrix base= new Matrix(Order);
        int flag,a=0,b=0;
        if(Order==2)
        {
            float buffer= this.Elements[0][0];
            this.Elements[0][0]=this.Elements[1][1];
            this.Elements[1][1]=buffer;
            this.Elements[1][0]*=(-1);
            this.Elements[0][1]*=(-1);
            buffer=this.Elements[1][0];
            this.Elements[1][0]=this.Elements[0][1];
            this.Elements[0][1]=buffer;
            this.SquareTranspose();
            Progress.setProgress(100);
        }
        else
        {
            for(int k=0;k<Order;k++)
            {
                Progress.setProgress((k*100)/Order);
                for(flag=0;flag<Order;flag++)
                {
                    Progress.setSecondaryProgress((flag*100)/Order);
                    Matrix pointer=new Matrix(Order-1);
                    for(int i=0;i<Order;i++)
                    {
                        for (int j = 0; j < Order; j++)
                        {
                            if ((flag != j) && (k != i))
                            {
                                float pg = this.Elements[i][j];
                                pointer.PushAt(a, b, pg);
                                b++;
                            }
                        }
                        if (k != i)
                            a++;
                        b = 0;
                    }
                    float z=(float) pointer.GetDeterminant();
                    int variable= k+flag;
                    base.Elements[k][flag]= (float) Math.pow((-1),variable)*z;
                    a=0;
                    b=0;
                }
            }
            this.CopyFrom(base);
            this.SquareTranspose();
            Progress.setProgress(100);
        }

    }

    public Matrix ReturnAdjoint(ProgressDialog updates) {
        Matrix Result= new Matrix(this.GetRow());
        this.CopyThisto(Result);
        Result.MakeAdjoint(updates);
        return  Result;
    }

    public Matrix Inverse(ProgressDialog progressDialog){
        float determinant= (float) this.GetDeterminant();
        if(determinant==0) //if determinant was to be zero
            return null;
        else {
            Matrix Result = this.ReturnAdjoint(progressDialog);
            for (int i = 0; i < this.GetRow(); i++)
                for (int j = 0; j < this.GetCol(); j++)
                    Result.Elements[i][j] /= determinant;
            return Result;
        }
    }

    public void Raiseto(int a){
        Matrix pi= new Matrix(this.GetRow());
        pi.MakeIdentity();
        if(a==0)
            this.MakeIdentity();
        else
        {
            try {
                for (int i = 0; i < a; i++) {
                    assert pi != null;
                    pi = pi.MultipyWith(this);
                }

            }catch (Exception e){
                Log.d("RaiseToError :","Non Square Matrix called the function raiseto()");
                e.printStackTrace();
                return;
            }
            this.CopyFrom(pi);
        }

    }

    public Bundle GetDataBundled() {
        Bundle AllInfo= new Bundle();
        AllInfo.putInt("ROW",this.GetRow());
        AllInfo.putInt("COL",this.GetCol());
        AllInfo.putFloatArray("VALUES",Compress(this.Elements,this.GetRow(),this.GetCol()));
        return AllInfo;
    }

    public float[] Compress(float[][] elements,int row, int col) {
        float resultant[] = new float[row*col];
        int a=0;
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++)
                resultant[a++] = elements[i][j];
        }
        return resultant;
    }

    public void SetFromBundle(Bundle bundle) throws ClassCastException {
        this.SetRow(bundle.getInt("ROW"));
        this.SetCol(bundle.getInt("COL"));
        this.Elements=Expand(bundle.getInt("ROW"),bundle.getInt("COL"),bundle.getFloatArray("VALUES"));
    }

    public float[][] Expand(int row, int col, float[] values) {
        float Values[][] =  new float[9][9];
        int a=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                Values[i][j] = values[a++];
        }
        return Values;
    }

    public int GetDrawable(Type t) {
        switch (t)
        {
            case Normal:
                return R.mipmap.normal;
            case Null:
                return R.mipmap.null2;
            case Identity:
                return R.mipmap.identity;
            case Diagonal:
                return R.mipmap.diagonal;

        }
        return 0;
    }

    public boolean isNull() {
        for(int i=0;i<this.GetRow();i++)
        {
            for(int j=0;j<this.GetCol();j++)
                if(this.Elements[i][j]!=0)
                    return false;
        }
        return true;
    }

    private boolean isIdentity() {
        for(int i=0;i<this.GetRow();i++)
        {
            for(int j=0;j<this.GetCol();j++)
                if((this.Elements[i][j]!=1 && i==j)&&(this.Elements[i][j]!=0))
                    return false;
        }
        return true;
    }

    private boolean isDiagonal() {
        int flag1=0;
        for(int i=0;i<this.GetRow();i++)
        {
            for(int j=0;j<this.GetCol();j++)
                if(i!=j && this.Elements[i][j]!=0)
                    return false;
                else
                    flag1++;
        }
        return flag1 != this.GetCol();
    }

    public float GetElementof(int r,int c) throws ExceptionInInitializerError{
        if(this.GetRow()<r || this.GetCol() < c || r < 0 || c < 0)
            throw new ExceptionInInitializerError();
        else
            return this.Elements[r][c];
    }

    public void SetElementof(float val,int r,int c) throws ExceptionInInitializerError{
        if(this.GetCol()<c || this.GetRow()<r || c < 0 || r < 0 )
            throw new ExceptionInInitializerError();
        else
            this.Elements[r][c]=val;
    }

    public void CloneFrom(Matrix p) {
        this.SetCol(p.GetCol());
        this.SetRow(p.GetRow());
        this.CopyFrom(p);
    }

    public Matrix ExactClone(String newname) {
        Matrix matrix = new Matrix(this.GetRow(),this.GetCol());
        this.CopyThisto(matrix);
        return matrix;
    }

    public void ScalarMultiply(float multiplier){
        for(int i=0;i<this.GetRow();i++)
            for(int j=0;j<this.GetCol();j++)
                this.Elements[i][j]*=multiplier;
    }

    public Matrix ReturnScaler(float ig){
        Matrix re  = new Matrix(this.GetRow(),this.GetCol());
        for(int i=0;i<this.GetRow();i++)
            //noinspection ManualArrayCopy
            for(int j=0;j<this.GetCol();j++)
                re.Elements[i][j]=this.Elements[i][j];
        re.ScalarMultiply(ig);
        return re;
    }
    public int GetRank() throws IllegalStateException{

        int rank,i,retest=1,grp,p,r,j;
        int ZeroList[] = new int[9];
        Matrix buffer = new Matrix(this.GetRow(),this.GetCol());
        buffer.Elements = this.Elements.clone();
        buffer.Rank_UpdateInitZeros(ZeroList);
        buffer.Rank_ArrangeMatrix(ZeroList);
        if(buffer.Elements[0][0]==0)
            throw new IllegalStateException("Rank Error");
        buffer.Rank_UpdateInitZeros(ZeroList);
        buffer.Rank_ScaleMatrix(ZeroList);
        while (retest == 1){
            grp=0;
            for(i=0;i<buffer.GetRow();++i)
            {

                p=0;
                while(ZeroList[i+p]==ZeroList[i+p+1]&&(i+p+1)<buffer.GetRow())
                {

                    grp=grp+1;
                    p=p+1;
                }

                if(grp!=0)
                {
                    while(grp!=0)
                    {
                        for(j=0;j<this.GetCol();++j)
                        {
                            buffer.Elements[i+grp][j]=buffer.Elements[i+grp][j]-buffer.Elements[i][j];
                        }
                        grp=grp-1;
                    }
                    break;
                }
            }
            buffer.Rank_UpdateInitZeros(ZeroList);
            buffer.Rank_ArrangeMatrix(ZeroList);
            buffer.Rank_UpdateInitZeros(ZeroList);
            buffer.Rank_ScaleMatrix(ZeroList);
            retest=0;
            for(r=0;r<this.GetRow();++r)
            {
                if(ZeroList[r]==ZeroList[r+1]&&r+1<this.GetRow())
                {
                    if(ZeroList[r]!=this.GetCol())
                        retest=1;
                }
            }

        }
        //currently buffer is row-echolean form of this Matrix
        Log.d("Echolean : ",buffer.toString());
        rank =0;
        for (i=0;i<this.GetRow();++i)
        {
            if (ZeroList[i]!=this.GetCol())
            {
                ++rank;
            }
        }

        return rank;
    }

    private void Rank_ArrangeMatrix(int initZeros[]){
        int l,reqrow=0,i,k,lastrow,tempvar,large;
        float rowtemp[] = new float[9];
        lastrow = this.GetRow()-1;
        for(l=0;l<this.GetRow();++l) {
            large = initZeros[0];
            for (i = 0; i < this.GetRow(); ++i) {
                if (large <= initZeros[i]) {
                    large = initZeros[i];
                    reqrow = i;
                }
            }
            initZeros[reqrow] = -1;
            tempvar = initZeros[reqrow];
            initZeros[reqrow] = initZeros[lastrow];
            initZeros[lastrow] = tempvar;

            for (k = 0; k < this.GetCol(); ++k) {
                rowtemp[k] = this.Elements[lastrow][k];
            }
            for (k = 0; k < this.GetCol(); ++k) {
                this.Elements[lastrow][k] = this.Elements[reqrow][k];
            }
            for (k = 0; k < this.GetCol(); ++k) {
                this.Elements[reqrow][k] = ((float) rowtemp[k]);
            }
            lastrow--;
        }

    }

    private void Rank_UpdateInitZeros(int initZeros[]){
        int zerocount,i,j;
        for(i=0;i<this.GetRow();i++){
            zerocount=0;
            for(j=0;(this.Elements[i][j]==0) && j<this.GetCol(); j++)
                zerocount++;
            initZeros[i]=zerocount;
        }
    }

    private void Rank_ScaleMatrix(int initZero[])
    {
        int i,j;
        float divisor;
        for(i=0;i<this.GetRow();i++){
            divisor = this.Elements[i][initZero[i]];
            for(j=initZero[i];j<this.GetCol();j++){
                this.Elements[i][j] /= divisor;
            }
        }
    }

    @Override
    public String toString(){
        String s = "--->";
        for(int i=0;i<this.GetRow();i++) {
            for (int j = 0; j < this.GetCol(); j++)
                s += String.valueOf(this.GetElementof(i, j)) + " : ";
            s += "->";
        }
        return s;
    }
}
