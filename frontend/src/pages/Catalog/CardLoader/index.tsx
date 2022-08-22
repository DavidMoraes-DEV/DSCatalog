import ContentLoader from 'react-content-loader';

import './styles.css';

const CardLoader = () => {
  return (
    <div className="row catalog-loader-container">
      {Array.from({ length: 12 }).map((_, index) => (
        <CardLoaderBase key={index} />
      ))}
    </div>
  );
};

function CardLoaderBase() {
  return (
    <div className="col-sm-6 col-md-4 col-xl-3 catalog-loader">
      <ContentLoader
        speed={2}
        width="100%"
        height="100%"
        viewBox="0 0 300 250"
        backgroundColor="#f3f3f3"
        foregroundColor="#ecebeb"
      >
        <rect x="0" y="0" rx="10" ry="10" width="100%" height="100%" />
      </ContentLoader>
    </div>
  );
}

export default CardLoader;
