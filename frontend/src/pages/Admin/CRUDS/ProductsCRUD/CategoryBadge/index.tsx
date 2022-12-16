import './styles.css';

type Props = {
  name: string;
};

const CategoryBadge = ({ name }: Props) => {
  return (
    <div className="category-badge-container">
      <h4>{name}</h4>
    </div>
  );
};

export default CategoryBadge;
