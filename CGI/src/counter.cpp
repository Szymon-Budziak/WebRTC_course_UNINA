#include <iostream>
#include <fstream>

using namespace std;

int main() {
    cout << "Content-Type: text/html" << endl;
    cout << endl;  // indicates the end of the HTTP header
    cout << "<HTML>" << endl;
    cout << "<HEAD>" << endl;
    cout << "  <TITLE>WebRTC class</TITLE>" << endl;
    cout << "</HEAD>" << endl;
    cout << "<BODY>" << endl;
    cout << "  <H1>Welcome to the WebRTC class!!</H1>" << endl;
    cout << "  <HR>" << endl;

    ifstream f1("./counter.txt");
    int c = 0;
    if (f1)
        f1 >> c;
    c++;
    f1.close();
    ofstream f2("./counter.txt");
    f2 << c;
    f2.close();

    cout << "<H3>Number of accesses: " << c << "</H3>" << endl;
    cout << "</BODY>" << endl;
    cout << "</HTML>" << endl;

    return 0;
}