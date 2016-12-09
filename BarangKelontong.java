import java.util.Scanner;

class Barang{
  public String kode;
  public String nama;
  public String dist; // Distributor
  public int harga;
  public Barang next;
}

class Menu{
  public static Scanner scan = new Scanner(System.in);
  public static int pilihan;
  public static int notambah;
  public static Barang baru;
  public static void home(){
    System.out.print("\n-:: BARANG KELONTONG ::-\n");
    System.out.print("1. Input Data\n");
    System.out.print("2. Lihat Data\n");
    System.out.print("3. Tambah Data\n");
    System.out.print("4. Hapus Data\n");
    System.out.print("6. Keluar\n");
    System.out.print("Masukkan pilihan: ");
    pilihan = scan.nextInt();
    System.out.print("\n");
  }
  public static void tambah(int jumlist){
    baru = new Barang();
    System.out.print("\n");
    System.out.print("-:: BARANG KELONTONG ::-\n");
    System.out.print(" :: TAMBAH DATA      ::\n");
    System.out.print("Kode        : ");
    baru.kode = scan.next();
    System.out.print("Nama        : ");
    baru.nama = scan.next();
    System.out.print("Distributor : ");
    baru.dist = scan.next();
    System.out.print("Harga       : ");
    baru.harga = scan.nextInt();
    System.out.print("Masukkan nomor masukan (1 s/d "+jumlist+"): ");
    notambah = scan.nextInt();
  }
  public static char input(){
    baru = new Barang();
    System.out.print("\n");
    System.out.print("-:: BARANG KELONTONG ::-\n");
    System.out.print(" :: INPUT DATA       ::\n");
    System.out.print("Kode        : ");
    baru.kode = scan.next();
    System.out.print("Nama        : ");
    baru.nama = scan.next();
    System.out.print("Distributor : ");
    baru.dist = scan.next();
    System.out.print("Harga       : ");
    baru.harga = scan.nextInt();
    System.out.print("Lagi? (y/t) :");
    char lagi = scan.next().charAt(0);
    return lagi;
  }
}

class BarangKelontong{
  public static Scanner scan  = new Scanner(System.in);
  public static Menu menu     = new Menu();
  public static Barang awal;
  public static Barang akhir;
  public static int banyakData;
  public static void kosongkanList(){
    awal = null;
    akhir= null;
    banyakData = 0;
  }
  public static void tambahDepan(Barang baru){
    if(awal == null){
      awal = baru;
      akhir= baru;
      baru.next = null;
    }else if(awal == akhir){
      awal = baru;
      awal.next = akhir;
    }else{
      baru.next = awal;
      awal = baru;
    }
    menu.baru = null;
    banyakData++;
  }
  public static void tambahBelakang(Barang baru){
    if(awal == null){
      awal = baru;
      akhir= baru;
      baru.next = null;
    }
    else if(awal == akhir){
      akhir = baru;
      awal.next = akhir;
    }else{
      Barang tulong = awal;
      while(tulong!=null){
        tulong = tulong.next;
        if(tulong.next == null) break;
      }
      akhir = baru;
      tulong.next = akhir;
      akhir.next = null;
    }
    menu.baru = null;
    banyakData++;
  }
  public static void tambahDiManaSaja(Barang baru, int notambah){
    if(notambah <= 1) tambahDepan(baru);
    else if(notambah > banyakData) tambahBelakang(baru);
    else{
      Barang tulong = awal;
      int n = 1;
      while(n<notambah && tulong != akhir){
        tulong = tulong.next;
        System.out.print(tulong.nama+" "+banyakData+" "+notambah+" "+n+"\n");
        if (tulong.next == null) break;
        n++;
      }
      baru = tulong.next;
      tulong.next = baru;
      banyakData++;
    }
    menu.baru = null;
  }
  public static void tampil(){
    if(awal == null)
      System.out.print("List kosong, mau menampilkan apa?\n");
    else if(awal == akhir){
      System.out.print("=============================================\n");
      System.out.print("No\tKode\tNama\tDistributor\tHarga\n");
      System.out.print("=============================================\n");
      System.out.print(banyakData+"\t"+awal.kode+"\t"+awal.nama+"\t"+awal.dist+"\t\t"+awal.harga);
    }else{
      Barang tulong = awal;
      int N = 1;
      System.out.print("=============================================\n");
      System.out.print("No\tKode\tNama\tDistributor\tHarga\n");
      System.out.print("=============================================\n");
      while(tulong!=null){
        System.out.print(N+"\t"+tulong.kode+"\t"+tulong.nama+"\t"+tulong.dist+"\t\t"+tulong.harga+"\n");
        tulong = tulong.next;
        N++;
      }
    }
  }
  public static void main(String[] args) {
    kosongkanList();
    int pilihan;
    do{
      menu.home();
      pilihan = menu.pilihan;
      if(pilihan == 1){
        kosongkanList();
        char lagi;
        do {
          lagi = menu.input();
          tambahBelakang(menu.baru);
        }while(lagi == 'y' || lagi == 'Y');
      }
      else if(pilihan == 2)
        tampil();
      else if(pilihan == 3){
        menu.tambah(banyakData);
        tambahDiManaSaja(menu.baru, menu.notambah);
      }
    }while(pilihan != 6);
    System.out.print("Terima kasih dan sampai jumpa.\n");
  }
}
