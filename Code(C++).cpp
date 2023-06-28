#include <bits/stdc++.h>
#define fi first
#define se second
#define pb push_back
#define all(v) v.begin(), v.end()
using namespace std;

using ll = long long;
using ld = long double;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using vi = vector<int>;
using vp = vector <pii>;
using vll = vector<ll>;
using vvi = vector< vector<int> >;
using vvll = vector< vector<ll> >;
using vb = vector<bool>;

int MAX_N = 4e5, MAX_LOG = 20;
vi logs;
vvi AND, OR;

void init(){
    logs.resize(MAX_N + 1);
    int l = 0, pot = 1;
    for(int i=1; i<=MAX_N; i++){
        if(pot + pot <= i) pot+=pot, l++;
        logs[i] = l;
    }
    return;
}

int qryAND(int l, int r){
    if(l == r) return AND[l][0];
    int pot = logs[r-l];
    return AND[l][pot]&AND[r-(1<<pot)+1][pot];
}

int qryOR(int l, int r){
    if(l == r) return OR[l][0];
    int pot = logs[r-l];
    return OR[l][pot]|OR[r-(1<<pot)+1][pot];
}

void solve(){
    int n;
    cin >> n;
    vi a(2*n);
    AND.resize(2*n, vi(MAX_LOG + 1, 0));
    OR.resize(2*n, vi(MAX_LOG + 1, 0));

    for(int i=0; i<n; i++){
        cin >> a[i];
        a[i + n] = a[i];

        AND[i][0] = AND[i+n][0] = OR[i][0] = OR[i+n][0] = a[i];
    }

    for(int pot=1; pot<=MAX_LOG; pot++){
        for(int i=0; i<n + n; i++){
            AND[i][pot] = AND[i][pot-1]&AND[ min(n+n-1, i + (1 << (pot-1))) ][pot-1];
            OR[i][pot] = OR[i][pot-1]|OR[ min(n+n-1, i + (1 << (pot-1))) ][pot-1];
        }
    }

    /*cout << "Test: ";
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){

            int and__ = a[i], or__ = a[i];
            for(int k=0; k<=j; k++) and__&=a[i + k], or__|=a[i + k];

            if(and__ != qryAND(i, i+j) || or__ != qryOR(i, i+j)){
                cout << "Failed " << i << " " << i+j << " " << and__ << " " << qryAND(i, i+j) << " " << or__ << " " << qryOR(i, i+j) << "\n";
            }
        }
    }*/

    int ini, fin, med, curr_ans=0, temp_ans, temp;
    for(int bit=29; bit>=0; bit--){
        for(int i=0; i<n; i++){
            ini = 1;
            fin = n;
            temp_ans = curr_ans + (1 << bit);
            while(ini != fin){
                med = (ini + fin)/2;
                temp = qryAND(i, i+med-1)&temp_ans;
                if(temp){
                    ini = med+1;
                }else{
                    fin = med;
                }
            }

            if(ini == n) continue;
            temp = qryOR(i+ini, i+n-1)&temp_ans;
            if(temp  != temp_ans) continue;
            curr_ans = temp_ans;
            break;
        }
    }

    cout << curr_ans << "\n";

    return;
}

int main()
{

    ios_base::sync_with_stdio(false);
    cin.tie(0);

    init();

    int t = 1;
    cin >> t;
    while(t--){
        solve();
    }

    return 0;
}