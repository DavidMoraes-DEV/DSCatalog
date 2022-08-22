import ContentLoader from 'react-content-loader';

const CardLoader = () => (
  <div className='card-loader-container'>
    <ContentLoader
      speed={2}
      width={300}
      height={460}
      viewBox="0 0 300 400"
      backgroundColor="#f3f3f3"
      foregroundColor="#ecebeb"
    >
      <rect x="0" y="0" rx="2" ry="2" width="300" height="300" />
    </ContentLoader>
  </div>
);

export default CardLoader;
