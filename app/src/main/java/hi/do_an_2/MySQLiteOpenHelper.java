    package hi.do_an_2;

    import android.annotation.SuppressLint;
    import android.content.ContentValues;
    import android.content.Context;
    import android.content.SharedPreferences;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;

    public class MySQLiteOpenHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "hoc_tieng_anh.db";
        private static final int DATABASE_VERSION = 1;

        // Table Nguoi_dung
        public static final String TABLE_NGUOI_DUNG = "Nguoi_dung";
        public static final String COLUMN_TEN_DANG_NHAP = "ten_dang_nhap";
        public static final String COLUMN_MAT_KHAU = "mat_khau";

        // Table Tu_vung

        public static final String TABLE_TU_VUNG = "Tu_vung";
        public static final String COLUMN_ID_TU_VUNG = "ID_tu_vung";
        public static final String COLUMN_TU_VUNG = "tu_vung";
        public static final String COLUMN_NGHIA_TU_VUNG = "nghia_tu_vung";

        // Table Nguoi_dung_Tu_vung
        public static final String TABLE_NGUOI_DUNG_TU_VUNG = "Nguoi_dung_Tu_vung";
        public static final String COLUMN_TEN_DANG_NHAP_IN_NGUOI_DUNG_TU_VUNG = "ten_dang_nhap";
        public static final String COLUMN_ID_TU_VUNG_IN_NGUOI_DUNG_TU_VUNG = "ID_tu_vung";


        private Context context;
        private SQLiteDatabase database;

        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        // Lấy đường dẫn CSDL từ bên ngoài ứng dụng
        private String getDatabasePath(){
            return context.getApplicationInfo().dataDir + "/databases/" +DATABASE_NAME;
        }

        // Phương thức mở CSDL
        public void openDatabase() {
            String path = getDatabasePath();
            database =SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }

        // Phương thức lấy dữ liệu từ table Nguoi_dung
        public Cursor getData(){
            if(database == null || !database.isOpen()){
                openDatabase();
            }
            String[] projection ={COLUMN_TEN_DANG_NHAP, COLUMN_MAT_KHAU};
            return database.query(TABLE_NGUOI_DUNG, projection, null, null, null, null, null);
        }

        // Phương thức đóng CSDL
        public void closeDatabase(){
            if(database !=null || database.isOpen()){
                database.close();
            }
        }

        // Phương thức thêm dữ liệu đăng ký vào bảng Nguoi_dung
        public void themDuLieuDangKyTableNguoidung(String ten_dang_nhap, String mat_khau){
            if(database == null || !database.isOpen()){
                openDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(COLUMN_TEN_DANG_NHAP, ten_dang_nhap);
            values.put(COLUMN_MAT_KHAU, mat_khau);
            database.insert(TABLE_NGUOI_DUNG, null, values);
        }

        // Phương thức kiểm tra thông tin đăng nhập
        public boolean kiemTraThongTinDangNhap(String ten_dang_nhap, String mat_khau) {
            // Kiểm tra xem csdl đã mở hay chưa, nếu chưa thì mở csdl
            if (database == null || !database.isOpen()) {
                openDatabase();
            }

            // Xây dựng câu truy vấn SQL để kiểm tra thông tin đăng nhập trong bảng Nguoi_dung
            // Sử dụng điều kiện WHERE
            String selection = COLUMN_TEN_DANG_NHAP + " = ? AND " + COLUMN_MAT_KHAU + " = ?";
            String[] selectionArgs = {ten_dang_nhap, mat_khau};

            // Thực hiện truy vấn bằng phương thức query
            // Trả về một đối tượng Cusor chứa kết quả của truy vấn
            Cursor cursor = database.query(TABLE_NGUOI_DUNG, null, selection, selectionArgs, null, null, null);

            // Kiểm tra xem Cusor có null hoặc ít nhất một bản ghi trong kết quả truy vấn hay không
            // Nếu có thì result sẽ đặt thành true, ngược lại sẽ là false
            boolean result;
            if (cursor != null && cursor.getCount() > 0) {
                result = true;
            } else {
                result = false;
            }

            // Đóng cusor để giải phóng tài nguyên
            if (cursor != null) {
                cursor.close();
            }

            // Trả về kết quả thông tin đăng nhập
            return result;
        }

        // Phương thức thêm dữ liệu đối tượng Tu_vung vào bảng Tu_vung
        public void themTuVung(Tu_vung tu_vung){
            if (database == null || !database.isOpen()){
                openDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(COLUMN_TU_VUNG, tu_vung.getTuVung());
            values.put(COLUMN_NGHIA_TU_VUNG, tu_vung.getNghiaTuVung());
            database.insert(TABLE_TU_VUNG, null, values);
        }

        // Lấy id từ vựng vừa thêm
        @SuppressLint("Range")
        public int layIDTuVung(){

            // Mở database nếu chưa mở
            if(database == null || !database.isOpen()){
                openDatabase();
            }
            int idTuVung = -1;
            SQLiteDatabase db = this.getReadableDatabase();

            // Sử dụng câu truy vấn SQLite để ID_tu_vung ở dòng cuối cùng
            String query = "SELECT ID_tu_vung FROM Tu_vung ORDER BY ID_tu_vung DESC LIMIT 1";
            Cursor cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()){
                idTuVung = cursor.getInt(cursor.getColumnIndex("ID_tu_vung"));
            }
            cursor.close();
            // database.close();
            return idTuVung;
        }

        // Phương thức thêm tên đăng nhập và id từ vựng vào bảng Nguoi_dung_Tu_vung
        public void themThongTinVaoBangNguoiDungTuVung(){

            // Kiểm tra database đã mở hay chưa
            if(database == null || !database.isOpen()){
                openDatabase();
            }

            // Lấy giá trị tên đăng nhập từ sharePerence về
            SharedPreferences sharedPreferences = context.getSharedPreferences("AppPref", Context.MODE_PRIVATE);
            String tenDangNhap = sharedPreferences.getString("tenDangNhap","");
            // Lấy id từ vựng trong csdl ra
            int idTuVung =  layIDTuVung();
            // Thêm dữ liệu vào table Nguoi_dung_Tu_vung
            ContentValues values = new ContentValues();
            values.put(COLUMN_TEN_DANG_NHAP_IN_NGUOI_DUNG_TU_VUNG, tenDangNhap);
            values.put(COLUMN_ID_TU_VUNG_IN_NGUOI_DUNG_TU_VUNG, idTuVung);
            database.insert(TABLE_NGUOI_DUNG_TU_VUNG, null, values);
        }

        // Hàm từ vựng và nghĩa từ vựng từ tên đăng nhập
        public List<Tu_vung> layThuatNguVaDinhNghiaTuTenDangNhap(Context context){

            // Kiểm tra database đã mở hay chưa
            if(database == null || !database.isOpen()){
                openDatabase();
            }

            // Lấy tên đăng nhập về
            SharedPreferences sharedPreferences = context.getSharedPreferences("AppPref", Context.MODE_PRIVATE);
            String tenDangNhap = sharedPreferences.getString("tenDangNhap","");

            // Khởi tạo danh sách từ vựng và định nghĩa
            List<Tu_vung> tuVungList = new ArrayList<>();

            // Xây dựng câu truy vấn SQL để lấy danh sách từ vựng kèm định nghĩa tương ứng
            String query = "SELECT Tu_vung.tu_vung, Tu_vung.nghia_tu_vung FROM Tu_vung " +
                    "INNER JOIN Nguoi_dung_Tu_vung ON Tu_vung.ID_tu_vung = Nguoi_dung_Tu_vung.ID_tu_vung "+
                    "WHERE Nguoi_dung_Tu_vung.ten_dang_nhap = ?";

            // Thực hiện truy vấn bằng phương thức query
            Cursor cursor = database.rawQuery(query, new String[]{tenDangNhap});

            // Lấy dữ liệu từ con trỏ
            if(cursor.moveToFirst()){
                do{
                    @SuppressLint("Range") String tuVung = cursor.getString(cursor.getColumnIndex("tu_vung"));
                    @SuppressLint("Range") String nghiaTuVung = cursor.getString(cursor.getColumnIndex("nghia_tu_vung"));
                    Tu_vung tuVungObject = new Tu_vung(tuVung, nghiaTuVung);
                    tuVungList.add(tuVungObject);
                }while (cursor.moveToNext());
            }

            // Đóng con trỏ để giải phóng tài nguyên
            cursor.close();

            // Trả về danh sách từ vựng và định nghĩa tương ứng
            return tuVungList;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

