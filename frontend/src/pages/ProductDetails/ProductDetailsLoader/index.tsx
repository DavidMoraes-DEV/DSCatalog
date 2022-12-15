import ContentLoader from 'react-content-loader';
import './styles.css';

const ProductDetailsLoarder = () => (
  <ContentLoader
    className="loader-card-details-content"
    viewBox="0 0 100% 280"
    height="100%"
    width="100%"
  >
    <rect
      x="0"
      y="0"
      rx="10"
      ry="10"
      width="100%"
      height="78%"
      padding-Botton="20px"
    />
    <rect x="4" y="81%" rx="0" ry="0" width="80%" height="5%" />
    <rect x="4" y="88%" rx="0" ry="0" width="60%" height="5%" />
    <rect x="4" y="95%" rx="0" ry="0" width="75%" height="5%" />
  </ContentLoader>
);

export default ProductDetailsLoarder;
