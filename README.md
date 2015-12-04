# Android-MVP-Pattern-Sample

## MVPについて
MVP(model・view・presenter)とはアーキテクチャの1つです。MVCとの違いは、コントローラーの代わりにプレゼンターを置きUIロジックをプレゼンターが引き受けビューはUIロジックを持たない事です。
MVP構成ではActivityやFragmentをコントローラではなくビューとして扱い、ビューからのイベントをプレゼンターが受け取り処理結果をビューに返す事で、実際のAndroidフレームワークに依存したUI操作とUIロジックを分離する事が出来ます。  
また、処理を役割ごとに明確にレイヤー分けして分離する事で、実装が分かりやすくUIロジックへのユニットテストが記述可能な設計になっています。  
各レイヤーごとにハブとなるクラスをViewと一対一で定義する事で、処理の流れを追いやすくなり、各レイヤーの差し替え・Mock化も容易になります。

## 各クラスの説明
### View
ViewにはActivity・Fragmentのメソッドを全て定義し、Presenterから呼び出せるようにします。

### Activity・Fragment
Viewを実装ActivityやFragmentです。  
Presenterを保持しています。  
ActivityやFragmentが行う役割は、ライフサイクルなどのイベントをPresenterに伝える事と、Presenterからの指示で画面を更新する事です。  
基本的にはレイアウトやAndroidAPIへのアクセス処理しか書かず、if文やfor文などのロジックが無くなるのが理想です。  

### Adapter
ListViewのAdapterにもViewのPresenterを持たせ、getViewなどのイベントをPresenterに渡します。  
その際にViewHolderとItemもPresenterに渡し、ViewHolderの更新はView(Activity・Fragment)内のメソッドで行います。  
このようにする事でUIロジックPresenter、画面の更新をViewに集約されAdapterの肥大化を防ぐことが出来ます。

### Presenter
PresenterではViewとUseCaseを持ちます。  
Presenterの役割はViewからのイベントを受け取り、表出判定などのUIロジックを行いViewへ画面更新指示を返す事です。  
UIに絡まない業務ロジックやデータの取得などはUseCaseクラスに任せます。  

### UseCase
UseCaseではRepositoryを持ち、画面固有の業務ロジックを担当します。  
シンプルな要件のアプリであればUseCaseの処理はほとんどRepositoryに移譲されるかもしれません。  
アプリの仕様が複雑だったりAPI側で柔軟な対応が出来ないアプリであれば、UseCaseで画面固有のリスト操作や文字列操作などを行います。

### Repository
RepositoryではAPIやデータベースなどへのデータアクセスを担当します。  
RepositoryクラスをUseCaseクラスとは別に定義する事でUseCaseのテストをしやすくします。

## MVPパッケージ構成
 以下にMVP構成にした場合の、Androidアプリケーションプロジェクトの推奨パッケージ構成を示します。

    jp
    ┗ co
       ┗ recruit
          ┗
           ┣ data ☆ここにxxxRepositoryを置く
           ┃   ┣ network
           ┃   ┣ storage
           ┃   ┗ preferences
           ┃
           ┣ domain ☆ここにxxxUseCaseを置く
           ┃  ┣ model
           ┃  ┣ exception
           ┃  ┗ common
           ┃
           ┣ presentation ☆ここにxxxPresenterを置く
           ┣ view ☆ここにxxxViewを置く
           ┃  ┣ receiver
           ┃  ┣ service
           ┃  ┣ activity
           ┃  ┣ fragment
           ┃  ┣ dialog
           ┃  ┣ adapter
           ┃  ┗ widget
           ┣ constant
           ┗ application

### ベースパッケージ
Applicationクラスはベースパッケージ直下に置く。  
Constantクラスはベースパッケージ直下に置く。  

### dataパッケージ    
xxxRepositoryクラスを配置する。  
DB、ファイル、メモリ等へのアクセス、WebApi関連のクラスはこのパッケージ以下に配置する。  

#### **networkパッケージ**
Webサービスアクセス関連のクラスを置く。  
内部の構成は任意。  

#### **storageパッケージ**
DBアクセス系のクラスを置く。  
内部の構成は任意。  

#### **preferencesパッケージ**
SharedPreferencesクラスアクセス関連のクラスを置く。  
内部の構成は任意。  

### domainパッケージ
xxxUseCaseクラスを配置する。
モデルやビジネスロジックはこのパッケージ以下に配置する。

#### **modelパッケージ**
entityやDtoを置く。  

#### **exceptionパッケージ**
Exceptionクラスを置く。

#### **commonパッケージ**
UtilなどのUseCaseから呼び出される画面に依存しないビジネスロジッククラスを置く。  
内部の構成は任意。  

### presentationパッケージ
xxxPresenterを配置する。  
UI関連のクラスはこのパッケージ以下に配置する。  

### viewパッケージ
xxxViewを配置する。  
画面表示関連のクラスやAndroidフレームワークに依存したクラスはこのパッケージ以下に配置する。  
アプリの規模が大きい場合は画面や機能ごとにサブパッケージを切ってもよい。  

#### **receiverパッケージ**
Receiver継承クラスを置く。クラス名はxxxReceiverとする。  

#### **serviceパッケージ**
Service継承クラスを置く。名はxxxServiceとする。  
aidlを使用する場合はIxxxService.aidlまたはIxxxCallback.aidlとしてservie/aidl配下に置く。

#### **activityパッケージ**
Activity継承クラスを置く。クラス名はxxxActivityにする。  

#### **fragmentパッケージ**
Fragment継承クラスを置く。クラス名はxxxFragmentにする。  

#### **dialogパッケージ**
Dialog継承クラスを置く。クラス名はxxxDialogにする。  

#### **adapterパッケージ**
ListViewやViewPagerのAdapterクラスを置く。名は～Adapterとする(staticであれば使用クラス内にインナークラスも可)。

#### **widgetパッケージ**
AndroidフレームワークのViewクラス継承クラス(CustomView)などを置く。  
内部の構成は任意。  

### View(Fragment)

```java
public class ShopListFragment extends ListFragment implements ShopListView, AbsListView.OnScrollListener {

    private static final String KEY_SERVICE_AREA_CODE = "key_servcie_area_code";
    private static final String KEY_SERVICE_AREA_NAME = "key_servcie_area_name";

    private ShopListPresenter presenter;

    private ShopListAdapter adapter;
    private View footer;

    public static ShopListFragment newInstance(@NonNull ServiceArea area) {
        ShopListFragment fragment = new ShopListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SERVICE_AREA_CODE, area.code);
        bundle.putString(KEY_SERVICE_AREA_NAME, area.name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new ShopListPresenter(new ShopListUseCase(new ShopListRepository(getActivity().getApplicationContext())));
        String serviceArea = getArguments().getString(KEY_SERVICE_AREA_CODE);
        if (serviceArea == null) {
            return;
        }
        presenter.onCreate(this, serviceArea);
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        presenter.onListItemClick(getItem(position), (ImageView) v.findViewById(R.id.icon));

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        presenter.onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_shop_list, getArguments().getString(KEY_SERVICE_AREA_NAME)));
        footer = View.inflate(getActivity(), R.layout.footer_loading, null);
        getListView().addFooterView(footer, null, false);
        getListView().setOnScrollListener(this);
        adapter = new ShopListAdapter(getActivity().getApplication(), presenter);
        setListAdapter(adapter);
    }

    @Override
    public void addShopList(List<ShopData> shopList) {
        adapter.addAll(shopList);
    }

    @Override
    public void removeFooter() {
        getListView().removeFooterView(footer);
    }

    @Override
    public ShopData getItem(int position) {
        return adapter.getItem(position);
    }

    @Override
    public int getFooterViewsCount() {
        return getListView().getFooterViewsCount();
    }

    @Override
    public void startShopDetailsActivity(ShopData shopData, Bitmap bitmap) {
        Intent intent = ShopDetailsActivity.newInstance(getActivity(), shopData, bitmap);
        ActivityCompat.startActivity(getActivity(), intent, null);
    }

    @Override
    public void initListViewCell(ShopListAdapter.ViewHolder holder, ShopData shop) {
        holder.genre.setText(shop.genre);
        holder.name.setText(shop.name);
        holder.access.setText(shop.mobile_access);

        Picasso.with(getActivity().getApplicationContext()).load(shop.pc_small_image).fit().centerCrop().into(holder.icon);
    }

    @Override
    public void setGenreVisibility(ShopListAdapter.ViewHolder holder, int visibility) {
        holder.genre.setVisibility(visibility);
    }
}
```

### View(interface)

```java
public interface ShopListView {

    void initViews();
    void addShopList(List<ShopData> shopList);
    void removeFooter();
    void startShopDetailsActivity(ShopData shopData, Bitmap bitmap);
    ShopData getItem(int position);
    int getFooterViewsCount();
    void initListViewCell(ShopListAdapter.ViewHolder holder, ShopData shop);
    void setGenreVisibility(ShopListAdapter.ViewHolder holder, int visibility);
}

```

### Presenter

```java
public class ShopListPresenter implements OnFinishedListener<GourmetRoot> {

    private ShopListUseCase useCase;
    private ShopListView view;

    private int start = 1;
    private String serviceAreaCode;

    public ShopListPresenter(ShopListUseCase useCase) {
        this.useCase = useCase;
    }

    public void onCreate(@NonNull ShopListView shopListView, @NonNull String serviceAreaCode) {
        this.view = shopListView;
        this.serviceAreaCode = serviceAreaCode;

        view.initViews();
        useCase.getShopListByServiceArea(serviceAreaCode, start, this);
    }

    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onFinished(GourmetRoot gourmetResult) {
        if (view == null) return;

        if (gourmetResult.results.results_available <= gourmetResult.results.results_returned + gourmetResult.results.results_start - 1) {
            view.removeFooter();
        }

        List<Shop> serviceAreaList = gourmetResult.results.shop;
        List<ShopData> shopList = new ArrayList<>();
        for (Shop shop : serviceAreaList) {
            shopList.add(new ShopData(shop));
        }
        view.addShopList(shopList);
        start += gourmetResult.results.results_returned;
    }


    public void onListItemClick(ShopData shop, ImageView iconView) {
        if (view == null) return;

        Bitmap bitmap = null;
        if (iconView.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) iconView.getDrawable()).getBitmap();
        }
        view.startShopDetailsActivity(shop, bitmap);
    }

    public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view == null) return;

        boolean isLastItemVisible = totalItemCount == firstVisibleItem + visibleItemCount;
        if (isLastItemVisible && view.getFooterViewsCount() > 0) {
            useCase.getShopListByServiceArea(serviceAreaCode, start, this);
        }
    }

    public void onGetView(ShopListAdapter.ViewHolder holder, ShopData shop) {
        view.initListViewCell(holder, shop);
        if (shop.genre == null) {
            view.setGenreVisibility(holder, View.INVISIBLE);
        } else {
            view.setGenreVisibility(holder, View.VISIBLE);
        }
    }
}

```

### UseCase

```java
public class ShopListUseCase {

    private ShopListRepository repository;

    public ShopListUseCase(ShopListRepository repository) {
        this.repository = repository;
    }

    public void getShopListByServiceArea(@NonNull String key, int start, @NonNull final OnFinishedListener<GourmetRoot> listener) {
        repository.getShopListByServiceArea(key, start, listener);
    }
}

```

### Repository

```java
public class ShopListRepository {

    private Context context;

    public ShopListRepository(Context context) {
        this.context = context;
    }

    public void getShopListByServiceArea(String key, int start, final OnFinishedListener<GourmetRoot> listener) {
        GourmetApi gourmetApi = RetroFitUtil.createRestAdapterBuilder(context).build().create(GourmetApi.class);
        gourmetApi.getShopListByServiceArea(key, start, new Callback<GourmetRoot>() {
            @Override
            public void success(GourmetRoot gourmetRoot, Response response) {
                listener.onFinished(gourmetRoot);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
}


```

## UnitTestについて
MVPのテストにはMockitoというモックライブラリを使用すると非常にテストが書きやすくなります。  
http://mockito.org/

### Mockitoを使用したUnitTest(Presenter)

```java
@RunWith(AndroidJUnit4.class)
public class ShopListPresenterTest {

    private ShopListUseCase useCaseMock;
    private ShopListView viewMock;

    private GourmetRoot gourmetRoot;

    @Before
    public void setup() {
        useCaseMock = mock(ShopListUseCase.class);
        viewMock = mock(ShopListView.class);

        gourmetRoot = new GourmetRoot();
        gourmetRoot.results = new GourmetResult();
        gourmetRoot.results.results_available = 100;
        gourmetRoot.results.results_returned = 20;
        gourmetRoot.results.results_start = 1;
        gourmetRoot.results.shop = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Shop shop = new Shop();
            shop.id = i + "";
            shop.name = "name:" + i;
            shop.access = "access:" + i;
            shop.photo = new Photo();
            shop.photo.pc = new PcPhoto();
            shop.photo.mobile = new MobilePhoto();
            shop.genre = new Genre();
            gourmetRoot.results.shop.add(shop);
        }
    }

    @Test
    public void takeViewTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.takeView(viewMock, "01");
        verify(viewMock, times(1)).initViews();
        verify(useCaseMock, times(1)).getShopListByServiceArea("01", 1, presenter);
    }

    @Test
    public void onFinishedTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.takeView(viewMock, "01");
        presenter.onFinished(gourmetRoot);
        verify(viewMock, times(1)).addShopList(any(List.class));
        verify(viewMock, never()).removeFooter();
    }

    @Test
    public void removeFooterTest() {
        // 現在の取得数がavailableより少なければremoveFooterは呼ばれない
        gourmetRoot.results.results_available = 21;
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.takeView(viewMock, "01");
        presenter.onFinished(gourmetRoot);
        verify(viewMock, never()).removeFooter();

        // 現在の取得数がavailableと同じならremoveFooterが呼ばれる
        gourmetRoot.results.results_available = 20;
        presenter.onFinished(gourmetRoot);
        verify(viewMock, times(1)).removeFooter();
    }

    @Test
    public void onScrollTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.takeView(viewMock, "01");
        presenter.onFinished(gourmetRoot);
        when(viewMock.getFooterViewsCount()).thenReturn(1);

        // firstVisibleItem + visibleItemCountがtotalItemCountと同じになるまでは更読みリクエストは呼ばれない
        presenter.onScroll(0, 0, 20);
        presenter.onScroll(1, 2, 20);
        presenter.onScroll(10, 3, 20);
        presenter.onScroll(14, 3, 20);
        presenter.onScroll(15, 3, 20);
        presenter.onScroll(16, 3, 20);
        verify(useCaseMock, times(1)).getShopListByServiceArea(anyString(), anyInt(), any(ShopListPresenter.class));
        verify(viewMock, times(1)).addShopList(any(List.class));

        // firstVisibleItem + visibleItemCountがtotalItemCountと同じになれば呼ばれる
        presenter.onScroll(17, 3, 20);
        verify(useCaseMock, times(2)).getShopListByServiceArea(anyString(), anyInt(), any(ShopListPresenter.class));

        presenter.onFinished(gourmetRoot);
        verify(viewMock, times(2)).addShopList(any(List.class));
    }

    @Test
    public void onListItemClickTest() {
        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.takeView(viewMock, "01");

        // IconのImageViewにBitmapが設定されていなければBitmapはnullで画面遷移が呼ばれる
        ShopData shopData = new ShopData(gourmetRoot.results.shop.get(0));
        ImageView imageView = new ImageView(InstrumentationRegistry.getContext());
        presenter.onListItemClick(shopData, imageView);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, null);

        // IconのImageViewにBitmapが設定されていればImageViewのBitmapを引数に画面遷移が呼ばれる
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        imageView.setImageBitmap(bitmap);
        presenter.onListItemClick(shopData, imageView);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, null);
        verify(viewMock, times(1)).startShopDetailsActivity(shopData, bitmap);
    }

    @Test
    public void onGetViewTest() {
        ShopData shop = new ShopData();
        ShopListAdapter.ViewHolder holder = mock(ShopListAdapter.ViewHolder.class);
        shop.genre = null;

        ShopListPresenter presenter = new ShopListPresenter(useCaseMock);
        presenter.takeView(viewMock, "01");
        presenter.onGetView(holder, shop);
        verify(viewMock, times(1)).initListViewCell(holder, shop);
        verify(viewMock, times(1)).setGenreVisibility(holder, View.INVISIBLE);
        verify(viewMock, never()).setGenreVisibility(holder, View.VISIBLE);

        shop.genre = "ジャンル";
        presenter.onGetView(holder, shop);
        verify(viewMock, times(2)).initListViewCell(holder, shop);
        verify(viewMock, times(1)).setGenreVisibility(holder, View.INVISIBLE);
        verify(viewMock, times(1)).setGenreVisibility(holder, View.VISIBLE);
    }
}
```
